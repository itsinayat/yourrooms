package com.inayat.yourrooms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inayat.yourrooms.dto.BOOKING_STATUS;
import com.inayat.yourrooms.dto.BookingDTO;
import com.inayat.yourrooms.dto.BookingResponse;
import com.inayat.yourrooms.dto.CHECKIN_CHECKOUT_STATUS;
import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.dto.ReviewAndRatingsDTO;
import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Bookings;
import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Coupons;
import com.inayat.yourrooms.entity.Hotels;
import com.inayat.yourrooms.entity.ReviewAndRatings;
import com.inayat.yourrooms.entity.Rooms;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.BookingRepository;
import com.inayat.yourrooms.repositories.BookingTransactionRepository;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.CouponRepository;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.ReviewAndRatingsRepository;
import com.inayat.yourrooms.repositories.RoomsRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.translator.BookingResponseTranslator;
import com.inayat.yourrooms.translator.RoomsTranslator;
import com.inayat.yourrooms.utils.EncryptionUtil;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import com.instamojo.wrapper.model.Refund;

@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private UserService userService;
	@Autowired
	RoomsRepository roomsRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	CouponRepository couponRepository;
	@Autowired
	ConfigurationRepository configurationRepository;
	@Autowired
	PaymentService paymentService;
	@Autowired
	BookingTransactionRepository bookingTransactionRepository;

	@Autowired
	ReviewAndRatingsRepository reviewAndRatingsRepository;

	public ApiResponse addOrUpdate(HotelDTO hotel) {
		User u = userService.getCurrentUser();
		if (hotel.getId() == null) {
			Hotels dao = new Hotels();
			if (hotel.getHotelName() != null)
				dao.setHotelName(hotel.getHotelName());
			if (hotel.getAddress() != null)
				dao.setAddress(hotel.getAddress());
			if (hotel.getLattitude() != null)
				dao.setLattitude(hotel.getLattitude());
			if (hotel.getLongitude() != null)
				dao.setLongitude(hotel.getLongitude());
			if (hotel.getPayAtHotel() != null)
				dao.setPayAtHotel(hotel.getPayAtHotel());
			if (hotel.getFreeBreakFast() != null)
				dao.setFreeBreakFast(hotel.getFreeBreakFast());
			if (hotel.getFreeWifi() != null)
				dao.setFreeWifi(hotel.getFreeWifi());
			if (hotel.getCoupleFriendly() != null)
				dao.setCoupleFriendly(hotel.getCoupleFriendly());
			if (hotel.getInitialPrice() != null)
				dao.setInitialPrice(hotel.getInitialPrice());
			if (hotel.getDiscountPrice() != null)
				dao.setDiscountPrice(hotel.getDiscountPrice());
			if (hotel.getDiscountPrice() != null)
				dao.setCity(hotel.getCity());
			if (hotel.getPincode() != null)
				dao.setPincode(hotel.getPincode());
			if (hotel.getAc() != null)
				dao.setAc(hotel.getAc());
			dao.setCreate_user_id(u.getId());
			dao.setUpdate_user_id(u.getId());
			dao.setDel_ind(false);
			hotelRepository.save(dao);
			return new ApiResponse(543, "SUCCESS");
		} else {
			Optional<Hotels> h = hotelRepository.findById(hotel.getId());
			if (h.isPresent()) {
				Hotels dao = h.get();
				if (hotel.getHotelName() != null)
					dao.setHotelName(hotel.getHotelName());
				if (hotel.getAddress() != null)
					dao.setAddress(hotel.getAddress());
				if (hotel.getLattitude() != null)
					dao.setLattitude(hotel.getLattitude());
				if (hotel.getLongitude() != null)
					dao.setLongitude(hotel.getLongitude());
				if (hotel.getPayAtHotel() != null)
					dao.setPayAtHotel(hotel.getPayAtHotel());
				if (hotel.getFreeBreakFast() != null)
					dao.setFreeBreakFast(hotel.getFreeBreakFast());
				if (hotel.getFreeWifi() != null)
					dao.setFreeWifi(hotel.getFreeWifi());
				if (hotel.getCoupleFriendly() != null)
					dao.setCoupleFriendly(hotel.getCoupleFriendly());
				if (hotel.getInitialPrice() != null)
					dao.setInitialPrice(hotel.getInitialPrice());
				if (hotel.getDiscountPrice() != null)
					dao.setDiscountPrice(hotel.getDiscountPrice());
				if (hotel.getDiscountPrice() != null)
					dao.setCity(hotel.getCity());
				if (hotel.getPincode() != null)
					dao.setPincode(hotel.getPincode());
				dao.setUpdate_user_id(u.getId());
				hotelRepository.save(dao);
				return new ApiResponse(543, "SUCCESS");
			} else {
				return new ApiResponse(543, "Hotel Doesn't Exists");

			}

		}

	}

	public ApiResponse getAllHotel(String city, String pincode) throws JsonProcessingException {
		if (city != null && pincode == null) {
			List<Hotels> list = hotelRepository.findByCity(city);
			return new ApiResponse(343, "SUCCESS", list);
		} else if (pincode != null && city == null) {
			List<Hotels> list = hotelRepository.findByPinCode(pincode);
			return new ApiResponse(343, "SUCCESS", list);
		} else if (pincode != null && city != null) {
			List<Hotels> list = hotelRepository.findByPinCodeAndCity(pincode, city);
			return new ApiResponse(343, "SUCCESS", list);
		} else {
			Iterable<Hotels> hotellist = hotelRepository.findAll();
			List<HotelDTO> list = new ArrayList<>();
			for (Hotels h : hotellist) {
				HotelDTO dto = new HotelDTO();
				dto.setAc(h.getAc());
				dto.setAddress(h.getAddress());
				dto.setCity(h.getCity());
				dto.setCoupleFriendly(h.getCoupleFriendly());
				dto.setCreate_dt(h.getCreate_dt());
				dto.setCreate_user_id(h.getCreate_user_id());
				dto.setDel_ind(h.getDel_ind());
				dto.setDiscountPrice(h.getDiscountPrice());
				dto.setFreeBreakFast(h.getFreeBreakFast());
				dto.setFreeWifi(h.getFreeWifi());
				dto.setHotelName(h.getHotelName());
				dto.setId(h.getId());
				dto.setImages(h.getImages());
				dto.setInitialPrice(h.getInitialPrice());
				dto.setLattitude(h.getLattitude());
				dto.setLongitude(h.getLongitude());
				dto.setPayAtHotel(h.getPayAtHotel());
				dto.setPincode(h.getPincode());
				dto.setRating(h.getRating());
				dto.setUpdate_dt(h.getUpdate_dt());
				dto.setUpdate_user_id(h.getUpdate_user_id());

				List<ReviewAndRatings> g = reviewAndRatingsRepository.findByHotel(h);
				List<ReviewAndRatingsDTO> list1 = new ArrayList<>();

				for (ReviewAndRatings r : g) {
					ReviewAndRatingsDTO rr = new ReviewAndRatingsDTO();
					rr.setComment(r.getComment());
					rr.setDel_ind(r.getDel_ind());
					rr.setId(r.getId());
					rr.setRating(r.getRating());
					list1.add(rr);
				}
				dto.setReviewAndRatings(list1);
				list.add(dto);
			}
			return new ApiResponse(343, "SUCCESS", hotellist);

		}
	}

	public ApiResponse addRoomsTOHotel(RoomsDTO dto) {
		Optional<Hotels> h = hotelRepository.findById(dto.getHotelId());

		if (!h.isPresent()) {
			return new ApiResponse(413, "Hotel not found");
		}
		Hotels hotel = h.get();

		if (dto.getId() == null) {
			Rooms room = RoomsTranslator.translateToDao(dto, hotel);
			room.setCreate_user_id(userService.getCurrentUser().getId());
			room.setUpdate_user_id(userService.getCurrentUser().getId());
			roomsRepository.save(room);
			return new ApiResponse(432, "ADDED ROOM");
		} else {
			Optional<Rooms> r = roomsRepository.findById(dto.getId());
			if (!r.isPresent()) {
				return new ApiResponse(432, "Room Not Found");
			}
			Rooms dao = r.get();

			dao.setBalconyAvl(dto.getBalconyAvl());
			dao.setDoubleBed(dto.getDoubleBed());
			dao.setFreeCancellation(dto.getFreeCancellation());
			dao.setHotel(hotel);
			dao.setName(dto.getName());
			dao.setOccupacy(dto.getOccupacy());
			dao.setReserved(false);
			dao.setRoomSize(dto.getRoomSize());
			dao.setRoomType(dto.getRoomType());
			dao.setInitialPrice(dto.getInitialPrice());
			dao.setDiscountPrice(dto.getDiscountPrice());
			dao.setUpdate_user_id(userService.getCurrentUser().getId());
			roomsRepository.save(dao);
			return new ApiResponse(432, "ROOM UPDATED");
		}

	}

	public ApiResponse getAllHotelRooms(Long hotel_id) {
		Optional<Hotels> hotelopt = hotelRepository.findById(hotel_id);
		if (!hotelopt.isPresent()) {
			return new ApiResponse(413, "Hotel not found");
		}
		Hotels hotel = hotelopt.get();
		List<Rooms> rooms = roomsRepository.findAllByHotel(hotel);
		return new ApiResponse(322, "SUCCESS", rooms);
	}

	public ApiResponse getRoom(Long room_id) {
		Optional<Rooms> rooms = roomsRepository.findById(room_id);

		if (!rooms.isPresent()) {
			return new ApiResponse(413, "Room not found");
		}
		return new ApiResponse(322, "SUCCESS", RoomsTranslator.translateToDTO(rooms.get()));
	}

	public ApiResponse BookRoom(BookingDTO request) throws IOException {
		Optional<User> u = userRepository.findById(request.getUserId());
		if (!u.isPresent()) {
			return new ApiResponse(432, "User Not Found");
		}

		int totalAVL = 0;
		double initialPriceTotal = 0;
		double discountPriceTotal = 0;

		Long[] ids = request.getRooms();
		for (Long id : ids) {
			Optional<Rooms> room = roomsRepository.findById(id);
			if (!room.isPresent()) {
				return new ApiResponse(432, "Room Id is Not Valid");
			}
			Rooms roomdetails = room.get();
			totalAVL += roomdetails.getOccupacy();
			initialPriceTotal += roomdetails.getInitialPrice();
			discountPriceTotal += roomdetails.getDiscountPrice();
		}
		if (totalAVL < request.getNoOfGuests()) {
			return new ApiResponse(432, "No sufficient rooms are avaialable");
		}

		Bookings dao = new Bookings();
		dao.setCreate_user_id(userService.getCurrentUser().getId());
		dao.setNoOfGuests(request.getNoOfGuests());
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(ids);
		dao.setRooms(jsonStr);
		dao.setUpdate_user_id(userService.getCurrentUser().getId());
		dao.setUser(u.get());
		dao.setCheckinDate(request.getCheckinDate());
		dao.setCheckoutDate(request.getCheckoutDate());
		dao.setBooking_price(initialPriceTotal);
		dao.setDiscount_price(discountPriceTotal);
		dao.setGst(calculateGst(initialPriceTotal - discountPriceTotal));
		dao.setDel_ind(false);
		dao.setCheckout_status(CHECKIN_CHECKOUT_STATUS.PENDING.toString());
		dao.setCheckin_status(CHECKIN_CHECKOUT_STATUS.PENDING.toString());
		Bookings newbooking = bookingRepository.save(dao);
		BookingResponse dto = BookingResponseTranslator.translateToDTO(newbooking);
		return new ApiResponse(432, "SUCCESS", dto);
	}

	public ApiResponse applyCoupon(String code, Long bookingId) throws IOException {
		Coupons c = couponRepository.findBycode(code);
		if (c == null) {
			return new ApiResponse(545, "Invalid Coupon");
		} else {
			Optional<Bookings> bks = bookingRepository.findById(bookingId);
			if (!bks.isPresent()) {
				return new ApiResponse(545, "Invalid booking Id");
			} else {
				Bookings bookings = bks.get();
				bookings.setDiscount_coupon(code);
				bookings.setCoupon_discount(c.getValue());
				Double gst = calculateGst(
						bookings.getBooking_price() - bookings.getDiscount_price() - bookings.getCoupon_discount());
				bookings.setGst(gst);
				BookingResponse resp = BookingResponseTranslator.translateToDTO(bookings);
				bookingRepository.save(bookings);
				return new ApiResponse(545, "Coupon Applied", resp);
			}

		}

	}

	private Double calculateGst(Double finalPrice) {
		Configuration c = configurationRepository.findByKey("GST_RATE");
		String rate = c.getValue();
		Double gst = finalPrice * (Double.parseDouble(rate) / 100);
		return gst;
	}

	public ApiResponse payment(BookingDTO booking) {
		String cd = String.valueOf(booking.hashCode());

		Optional<Bookings> bookings = bookingRepository.findById(booking.getId());
		if (!bookings.isPresent()) {
			return new ApiResponse(646, "BookingId Not Found");
		}

		List<BookingTransaction> bt = bookingTransactionRepository.findByBookingIdAndPaymentHash(bookings.get(), cd);
		for (BookingTransaction b : bt) {
			if (b.getPaymentHash().equals(String.valueOf(booking.hashCode()))) {
				return new ApiResponse(646, "Payment Already Initiated", getPaymentByOrderId(b.getOrder_id()));
			}
		}
		bookings.get().getTransaction();

		Optional<User> c = userRepository.findById(bookings.get().getUser().getId());
		if (!c.isPresent()) {
			return new ApiResponse(646, "User Not Found");
		}
		PaymentOrderResponse resp = null;

		try {
			resp = paymentService.createOrder(bookings.get(), booking.hashCode());
		} catch (Exception e) {
			return new ApiResponse(67, "Failed", e.getMessage());
		}

		if (resp != null) {
			return new ApiResponse(67, "SUCCESS", resp);
		} else {
			return new ApiResponse(674, "FAILED");
		}

	}

	public ApiResponse getPaymentByOrderId(String orderId) {
		PaymentOrder paymentOrder = paymentService.getPaymentByOrderId(orderId);
		if (paymentOrder != null) {
			return new ApiResponse(432, "SUCCESS", paymentOrder);
		}
		return new ApiResponse(432, "OrderId Not present");
	}

	public ApiResponse createRefund(String paymentId, double refundAmount) {
		/*
		 * Create a new refund
		 */
		Refund refund = new Refund();
		refund.setPaymentId(paymentId);
		refund.setStatus("refunded");
		refund.setType("RFD");
		refund.setBody("This is a test refund.");
		refund.setRefundAmount(refundAmount);

		try {
			Refund createdRefund = paymentService.getPaymentApi().createRefund(refund);
			return new ApiResponse(432, "SUCCESS", createdRefund);

		} catch (HTTPException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
			System.out.println(e.getJsonPayload());

		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
		return new ApiResponse(509, "FAILED");
	}

	public ApiResponse getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	public ApiResponse updateOrder(String payment_id, String payment_status, String id, String hash) {
		if (hash != null) {
			Map<String, String> map = getEncryptedParam(hash);
			PaymentOrder f = paymentService.getPaymentByOrderId(map.get("id"));
			System.out.println(map);
			BookingTransaction bt = bookingTransactionRepository.findByOrderId(f.getId());
			if (bt == null) {
				return new ApiResponse(3421, "order ID not found");
			}
			bt.getBooking().setPaymentStatus(f.getPayments().get(0).getStatus());
			bt.setPaymentId(map.get("payment_id"));
			bt.setPaymentStatus(map.get("payment_status"));
			bt.setReference_id(f.getTransactionId());
			bt.setPaidAmount(f.getAmount().longValue());
			bt.setPaymentStatus(f.getPayments().get(0).getStatus());
			bt.setPayment_mode("ONLINE");
			bookingTransactionRepository.save(bt);
			return new ApiResponse(3421, "SUCCESS", f);
		} else {
			BookingTransaction bt = bookingTransactionRepository.findByOrderId(id);
			PaymentOrder f = paymentService.getPaymentByOrderId(id);
			bt.getBooking().setPaymentStatus(payment_status);
			bt.setPaymentId(payment_id);
			bt.setPaymentStatus(payment_status);
			bt.setReference_id(f.getTransactionId());
			bt.setPaidAmount(f.getAmount().longValue());
			bt.setPaymentStatus(f.getPayments().get(0).getStatus());
			bt.setPayment_mode("ONLINE");
			bookingTransactionRepository.save(bt);
			return new ApiResponse(3421, "SUCCESS", f);

		}
	}

	public Map<String, String> getEncryptedParam(String param) {
		String x = EncryptionUtil.decode(param);
		Map<String, String> map = new HashMap<>();
		String[] s = x.split("&");
		for (String v : s) {
			String[] kv = v.split("=");
			map.put(kv[0], kv[1]);
		}
		return map;
	}

	public ApiResponse paymentPayAthotel(BookingDTO booking) {
		String cd = String.valueOf(booking.hashCode());

		Optional<Bookings> bookings = bookingRepository.findById(booking.getId());
		if (!bookings.isPresent()) {
			return new ApiResponse(646, "BookingId Not Found");
		}

		List<BookingTransaction> bt = bookingTransactionRepository.findByBookingIdAndPaymentHash(bookings.get(), cd);
		for (BookingTransaction b : bt) {
			if (b.getPaymentHash().equals(String.valueOf(booking.hashCode()))) {
				return new ApiResponse(646, "Payment Already Initiated", b.getOrder_id());
			}
		}

		Optional<User> c = userRepository.findById(bookings.get().getUser().getId());
		if (!c.isPresent()) {
			return new ApiResponse(646, "User Not Found");
		}
		BookingTransaction resp = null;

		try {
			resp = paymentService.createOrderPayAtHotel(bookings.get(), booking.hashCode());
		} catch (Exception e) {
			return new ApiResponse(67, "Failed", e.getMessage());
		}

		if (resp != null) {
			return new ApiResponse(67, "SUCCESS");
		} else {
			return new ApiResponse(674, "FAILED");
		}

	}

	public ApiResponse createHash(String payment_id, String payment_status, String id, String hash) {
		String hashs = EncryptionUtil
				.encode("payment_id=" + payment_id + "&payment_status=" + payment_status + "&id=" + id + "");
		return new ApiResponse(674, "Success", hashs);
	}

	public ApiResponse cancelBooking(String bookingId) {
		Bookings bookings = bookingRepository.findByUserAndBooking(userService.getCurrentUser(),
				Long.valueOf(bookingId));
		if (bookings == null) {
			return new ApiResponse(674, "Booking Not Found");
		}
		bookings.setBookingStatus(BOOKING_STATUS.CANCELLED.toString());
		bookingRepository.save(bookings);
		return new ApiResponse(674, "Booking CANCELLED");
	}

	public ApiResponse updateBooking(BookingDTO request) throws Exception {
		Optional<Bookings> bookings = bookingRepository.findById(request.getId());
		if (!bookings.isPresent()) {
			return new ApiResponse(674, "Booking Not Found");
		}
		Bookings booking = bookings.get();
		if(request.getBooking_price() != null)
			booking.setBooking_price(request.getBooking_price());
		
		if(request.getDel_ind() != null)
			booking.setDel_ind(request.getDel_ind());
		
		if(request.getBookingStatus() != null)
			booking.setBookingStatus(request.getBookingStatus());
		
		if(request.getCheckinDate() !=null)
			booking.setCheckinDate(request.getCheckinDate());
		
		if(request.getCheckinStatus() != null)
			booking.setCheckin_status(request.getCheckinStatus());
		
		if(request.getCheckoutDate() !=null)
			booking.setCheckoutDate(request.getCheckoutDate());
	
		
		if(request.getCheckoutStatus() !=null)
			booking.setCheckout_status(request.getCheckoutStatus());
	
		
		if(request.getCoupon_discount() != null)
			booking.setCoupon_discount(request.getCoupon_discount());
		
		if(request.getDiscount_price() !=null)
			booking.setDiscount_price(request.getDiscount_price());
		
		if(request.getGst() !=null)
			booking.setGst(request.getGst());
		
		if(request.getNoOfGuests() !=0)
			booking.setNoOfGuests(request.getNoOfGuests());
		
		if(request.getPaymentStatus() !=null)
			booking.setPaymentStatus(request.getPaymentStatus());
		
		if(request.getRooms() !=null) {
			ObjectMapper Obj = new ObjectMapper();
			String jsonStr;
			try {
				jsonStr = Obj.writeValueAsString(request.getRooms());
			} catch (Exception e) {
				throw new Exception(e);
			}
			booking.setRooms(jsonStr);
			
		}
		
		booking.setUpdate_user_id(userService.getCurrentUser().getId());
		bookingRepository.save(booking);
		
			
		return new ApiResponse(674, "Booking Updated");

	}
}
