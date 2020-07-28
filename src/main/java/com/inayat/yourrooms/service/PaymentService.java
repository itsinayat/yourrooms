package com.inayat.yourrooms.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.inayat.yourrooms.dto.BookingDTO;
import com.inayat.yourrooms.dto.PAYMENT_MODE;
import com.inayat.yourrooms.dto.PaymentStatus;
import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Booking;
import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.BookingRepository;
import com.inayat.yourrooms.repositories.BookingTransactionRepository;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.utils.Constants;
import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;

@Service
public class PaymentService {
	@Autowired
	ConfigurationRepository configurationRepository;
	@Autowired
	BookingTransactionRepository bookingTransactionReository;
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	UserService userService;

	public PaymentOrderResponse createOrder(Booking dao, int hashCode) throws Exception {

		Instamojo api = getPaymentApi();
		String message;

		/*
		 * Create a new payment order
		 */
		PaymentOrder order = new PaymentOrder();
		order.setName(dao.getUser().getFirstName() + " " + dao.getUser().getLastName());
		order.setEmail(dao.getUser().getEmail());
		order.setPhone(dao.getUser().getMobile());
		order.setCurrency("INR");
		order.setAmount(Double.valueOf(calculateAmountPayable(dao)));
		order.setDescription("PaymentFor BookingID: " + dao.getBookingId());
		Configuration ENV = configurationRepository.findByKey("ENV");
		if (ENV.getValue().equals("DEV")) {  
			System.out.println(configurationRepository.findByKey("DEV_HOST").getValue());
			order.setRedirectUrl(configurationRepository.findByKey("DEV_HOST").getValue() + "/payment/update-order");
		} else {
			order.setRedirectUrl(configurationRepository.findByKey("PROD_HOST").getValue() + "/payment/update-order");
			System.out.println(configurationRepository.findByKey("PROD_HOST").getValue() + "/payment/update-order");
		}

		order.setWebhookUrl("http://www.someurl.com/");
		order.setTransactionId(generateTrId());

		
		try {
			PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
			BookingTransaction bookingTransaction = new BookingTransaction();
			dao.setBookingStatus("PAYMENT CREATED");
			dao.setPaymentStatus("PENDING");
			bookingTransaction.setOrder_id(paymentOrderResponse.getPaymentOrder().getId());
			bookingTransaction.setTransaction_id(paymentOrderResponse.getPaymentOrder().getTransactionId());
			bookingTransaction.setTransaction_url(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
			bookingTransaction.setCreate_user_id(userService.getCurrentUser().getId());
			bookingTransaction.setDel_ind(false);
			bookingTransaction.setUpdate_user_id(userService.getCurrentUser().getId());
			bookingTransaction.setTotalAmount(paymentOrderResponse.getPaymentOrder().getAmount().longValue());
			bookingTransaction.setReference_id(paymentOrderResponse.getPaymentOrder().getTransactionId());
			bookingTransaction.setPaymentStatus(PaymentStatus.PENDING.toString());
			bookingTransaction.setDiscountType("NONE");
			bookingTransaction.setPaidAmount(0L);
			bookingTransaction.setPayment_mode("ONLINE");
			bookingTransaction.setBooking(dao);
			bookingTransaction.setPaymentHash(String.valueOf(hashCode));
			bookingTransactionReository.save(bookingTransaction);
			dao.setTransaction(bookingTransaction);
			bookingRepository.save(dao);
			return paymentOrderResponse;

		} catch (HTTPException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
			System.out.println(e.getJsonPayload());
			message = e.getJsonPayload();
			throw new Exception(message);

		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
			message = e.getMessage();
			throw new Exception(message);
		}

	}

	public static Double calculateAmountPayable(Booking dao) {
		Double initial_price = dao.getBooking_price();
		Double coupon_discount = dao.getCoupon_discount();
		Double discountPrice = dao.getDiscount_price();
		Double gst = dao.getGst();
		Double finalPrice = initial_price - coupon_discount - discountPrice;
		finalPrice = finalPrice + gst;
		return finalPrice;

	}

	private static String generateTrId() {
		String uuid = UUID.randomUUID().toString();
		return "TR" + uuid;
	}

	private static String generateOrderId() {
		String uuid = UUID.randomUUID().toString();
		return "PH" + uuid;
	}

	public PaymentOrder getPaymentByOrderId(String OrderId) {

		Instamojo api = getPaymentApi();
		/*
		 * Get details of payment order when order id is given
		 */
		try {
			PaymentOrder paymentOrder = api.getPaymentOrder(OrderId);
			return paymentOrder;

		} catch (HTTPException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
			System.out.println(e.getJsonPayload());

		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Instamojo getPaymentApi() {
		Configuration client_id = configurationRepository.findByKey("IM_CLIENT_ID");
		Configuration client_secret = configurationRepository.findByKey("IM_CLIENT_SECRET");

		ApiContext context = ApiContext.create(client_id.getValue(), client_secret.getValue(), ApiContext.Mode.TEST);
		Instamojo api = new InstamojoImpl(context);
		return api;
	}

	public BookingTransaction createOrderPayAtHotel(Booking dao, int hashCode) {
		BookingTransaction bookingTransaction = new BookingTransaction();
		String tid = generateTrId();
		String oid = generateOrderId();
		dao.setBookingStatus(PaymentStatus.SUCCESS.toString());
		dao.setPaymentStatus(PaymentStatus.SUCCESS.toString());
		bookingTransaction.setOrder_id(oid);
		bookingTransaction.setTransaction_id(tid);
		bookingTransaction.setTransaction_url("NONE");
		bookingTransaction.setCreate_user_id(userService.getCurrentUser().getId());
		bookingTransaction.setDel_ind(false);
		bookingTransaction.setUpdate_user_id(userService.getCurrentUser().getId());
		if (dao.getCoupon_discount() != null) {
			bookingTransaction.setTotalAmount(dao.getBooking_price().longValue() - dao.getDiscount_price().longValue()
					+ dao.getGst().longValue() - dao.getCoupon_discount().longValue());
		} else {
			bookingTransaction.setTotalAmount(dao.getBooking_price().longValue() - dao.getDiscount_price().longValue()
					+ dao.getGst().longValue());
		}
		bookingTransaction.setReference_id(tid);
		bookingTransaction.setPaymentStatus(PaymentStatus.SUCCESS.toString());
		bookingTransaction.setDiscountType("NONE");
		bookingTransaction.setPaidAmount(0L);
		bookingTransaction.setBooking(dao);
		bookingTransaction.setPaymentHash(String.valueOf(hashCode));
		bookingTransaction.setPayment_mode(PAYMENT_MODE.PAY_AT_HOTEL.toString());
		bookingTransactionReository.save(bookingTransaction);
		dao.setTransaction(bookingTransaction);
		bookingRepository.save(dao);
		return bookingTransaction;

	}

}
