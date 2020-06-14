package com.inayat.yourrooms.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inayat.yourrooms.dto.BOOKING_STATUS;
import com.inayat.yourrooms.dto.BookingDTO;
import com.inayat.yourrooms.dto.BookingResponse;
import com.inayat.yourrooms.dto.CHECKIN_CHECKOUT_STATUS;
import com.inayat.yourrooms.dto.CouponsRequest;
import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.dto.MapStaffRequest;
import com.inayat.yourrooms.dto.RefundRequest;
import com.inayat.yourrooms.dto.ReviewAndRatingsDTO;
import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.entity.Booking;
import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Coupon;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.Refunds;
import com.inayat.yourrooms.entity.ReviewAndRating;
import com.inayat.yourrooms.entity.Room;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.BookingRepository;
import com.inayat.yourrooms.repositories.BookingTransactionRepository;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.CouponRepository;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.RefundsRepository;
import com.inayat.yourrooms.repositories.ReviewAndRatingsRepository;
import com.inayat.yourrooms.repositories.RoomsRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.translator.BookingResponseTranslator;
import com.inayat.yourrooms.translator.RoomsTranslator;
import com.inayat.yourrooms.utils.EncryptionUtil;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.Payment;
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

	@Autowired
	RefundsRepository refundsRepository;

	public ApiResponse addOrUpdate(HotelDTO hotel) {
		User u = userService.getCurrentUser();
		if (hotel.getId() == null) {
			Hotel dao = new Hotel();
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
			if (hotel.getCity() != null)
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
			Optional<Hotel> h = hotelRepository.findById(hotel.getId());
			if (h.isPresent()) {
				Hotel dao = h.get();
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
				if (hotel.getCity() != null)
					dao.setCity(hotel.getCity());
				if (hotel.getPincode() != null)
					dao.setPincode(hotel.getPincode());
				if (hotel.getAc() != null)
					dao.setAc(hotel.getAc());
				if (hotel.getIsBlocked() != null)
					dao.setIsBlocked(hotel.getIsBlocked());

				hotelRepository.save(dao);
				return new ApiResponse(543, "SUCCESS");
			} else {
				return new ApiResponse(543, "Hotel Doesn't Exists");

			}

		}

	}

	public ApiResponse getAllHotel(String city, String pincode,String sortBy,String ascDesc) throws JsonProcessingException {
		List<Hotel> list = null;
		if (city != null && pincode == null) {
			if(null !=ascDesc && ascDesc.equals("asc")) {
			list = hotelRepository.findByCityASC(city,sortBy);
			}else if(null !=ascDesc && ascDesc.equals("desc")) {
			list = hotelRepository.findByCityDESC(city,sortBy);
			}else {
				list = hotelRepository.findByCityDESC(city,sortBy);
			}
			return new ApiResponse(343, "SUCCESS", list);
		} else if (pincode != null && city == null) {
			if(null !=ascDesc && ascDesc.equals("asc")) {
			list = hotelRepository.findByPinCodeASC(pincode,sortBy);
			}else if(null !=ascDesc && ascDesc.equals("desc")) {
				list = hotelRepository.findByPinCodeDESC(pincode,sortBy);
			}else {
				list = hotelRepository.findByCityDESC(city,sortBy);
			}
		} else if (pincode != null && city != null) {
			if(null !=ascDesc && ascDesc.equals("asc")) {
			list = hotelRepository.findByPinCodeAndCityASC(pincode, city,sortBy);
			}else if(null !=ascDesc && ascDesc.equals("desc")) {
				list = hotelRepository.findByPinCodeAndCityDESC(pincode, city,sortBy);
			}else {
				list = hotelRepository.findByCityDESC(city,sortBy);
			}
			return new ApiResponse(343, "SUCCESS", list);
		} else {
			ascDesc="desc";
			sortBy="id";
			Iterable<Hotel> hotellist = hotelRepository.findAll(Sort.by(ascDesc.equals("asc")?Sort.Direction.ASC:Sort.Direction.DESC, sortBy));
			
			return new ApiResponse(343, "SUCCESS", hotellist);

		}
		return new ApiResponse(343, "SUCCESS", list);
	}

	public ApiResponse addRoomsTOHotel(RoomsDTO dto) {
		Optional<Hotel> h = hotelRepository.findById(dto.getHotelId());

		if (!h.isPresent()) {
			return new ApiResponse(413, "Hotel not found");
		}
		Hotel hotel = h.get();

		if (dto.getId() == null) {
			Room room = RoomsTranslator.translateToDao(dto, hotel);
			room.setCreate_user_id(userService.getCurrentUser().getId());
			room.setUpdate_user_id(userService.getCurrentUser().getId());
			roomsRepository.save(room);
			return new ApiResponse(432, "ADDED ROOM");
		} else {
			Optional<Room> r = roomsRepository.findById(dto.getId());
			if (!r.isPresent()) {
				return new ApiResponse(432, "Room Not Found");
			}
			Room dao = r.get();

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
			dao.setReserved(dto.getReserved());
			dao.setDisabled(dto.getDisabled());
			roomsRepository.save(dao);
			return new ApiResponse(432, "ROOM UPDATED");
		}

	}

	public ApiResponse getAllHotelRooms(Long hotel_id) {
		Optional<Hotel> hotelopt = hotelRepository.findById(hotel_id);
		if (!hotelopt.isPresent()) {
			return new ApiResponse(413, "Hotel not found");
		}
		Hotel hotel = hotelopt.get();
		List<Room> rooms = roomsRepository.findAllByHotel(hotel);
		return new ApiResponse(322, "SUCCESS", rooms);
	}

	public ApiResponse getRoom(Long room_id) {
		Optional<Room> rooms = roomsRepository.findById(room_id);

		if (!rooms.isPresent()) {
			return new ApiResponse(413, "Room not found");
		}
		return new ApiResponse(322, "SUCCESS", rooms.get());
	}

	public ApiResponse BookRoom(BookingDTO request) throws IOException, ParseException {
		Optional<User> u = userRepository.findById(request.getUserId());
		if (!u.isPresent()) {
			return new ApiResponse(432, "User Not Found");
		}
		Optional<Hotel> hotels = hotelRepository.findById(request.getHotelId());
		if (!hotels.isPresent()) {
			return new ApiResponse(432, "Hotel Not Found");
		}

		boolean isReserved = false;
		double initialPriceTotal = 0;
		double discountPriceTotal = 0;

		Long[] ids = request.getRooms();
		for (Long id : ids) {
			Optional<Room> room = roomsRepository.findById(id);
			if (!room.isPresent()) {
				return new ApiResponse(432, "Room Id is Not Valid");
			}

			initialPriceTotal += room.get().getInitialPrice();
			discountPriceTotal += room.get().getDiscountPrice();
		}

		double totalprice = initialPriceTotal - discountPriceTotal;

		Booking dao = new Booking();
		dao.setCreate_user_id(userService.getCurrentUser().getId());
		dao.setNoOfGuests(request.getNoOfGuests());
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(ids);
		dao.setRooms(jsonStr);
		dao.setUpdate_user_id(userService.getCurrentUser().getId());
		dao.setUser(u.get());

		Date checkinDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getCheckinDate());
		Date checkoutDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getCheckoutDate());
		dao.setCheckinDate(checkinDate);
		dao.setCheckoutDate(checkoutDate);
		dao.setBooking_price(initialPriceTotal);
		dao.setDiscount_price(discountPriceTotal);
		dao.setGst(calculateGst(totalprice));
		dao.setDel_ind(false);
		dao.setCheckoutStatus(CHECKIN_CHECKOUT_STATUS.PENDING.toString());
		dao.setCheckinStatus(CHECKIN_CHECKOUT_STATUS.PENDING.toString());
		dao.setHotelId(hotels.get().getId());
		Booking newbooking = bookingRepository.save(dao);

		for (Long id : ids) {
			Optional<Room> rooms = roomsRepository.findById(id);
			Room room = rooms.get();
			room.setReserved(true);
			roomsRepository.save(room);
		}
		BookingResponse dto = BookingResponseTranslator.translateToDTO(newbooking);
		return new ApiResponse(432, "SUCCESS", dto);
	}

	public ApiResponse applyCoupon(String code, Long bookingId) throws IOException {
		Coupon c = couponRepository.findBycode(code);
		if (c == null) {
			return new ApiResponse(545, "Invalid Coupon");
		} else {
			Optional<Booking> bks = bookingRepository.findById(bookingId);
			if (!bks.isPresent()) {
				return new ApiResponse(545, "Invalid booking Id");
			} else {
				Booking bookings = bks.get();
				bookings.setDiscount_coupon(code);
				bookings.setCoupon_discount(c.getValue());
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

		Optional<Booking> bookings = bookingRepository.findById(booking.getId());
		if (!bookings.isPresent()) {
			return new ApiResponse(646, "BookingId Not Found");
		}
		if(bookings.get().getBookingStatus().equals("CANCELLED")) {
			return new ApiResponse(646, "FAILED:ATTEMPT TO PAY FOR CANCELLED BOOKING ");
		}

		List<BookingTransaction> bt = bookingTransactionRepository.findByBookingIdAndPaymentHash(bookings.get(), cd);
		for (BookingTransaction b : bt) {
			if (b.getPaymentHash().equals(String.valueOf(booking.hashCode()))) {
				return new ApiResponse(646, "Payment Already Initiated", bookings.get().getTransaction());
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

	public ApiResponse updateOrder(String payment_id, String payment_status, String id, String hash)
			throws JsonParseException, JsonMappingException, IOException {
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
			bt.getBooking().setPaymentStatus("SUCCESS");
			bookingTransactionRepository.save(bt);
			
			//updating order

			String s = bt.getBooking().getRooms();
			ObjectMapper mapper = new ObjectMapper();
			Long[] rooms = mapper.readValue(s, Long[].class);
			for (long r : rooms) {
				Optional<Room> rms = roomsRepository.findById(Long.valueOf(r));
				if (rms.isPresent()) {
					Room room = rms.get();
					room.setReserved(true);
					roomsRepository.save(room);
				}
			}

			return new ApiResponse(3421, "SUCCESS", f);
		} else {
			BookingTransaction bt = bookingTransactionRepository.findByOrderId(id);
			PaymentOrder f = paymentService.getPaymentByOrderId(id);
			bt.getBooking().setPaymentStatus(payment_status);
			bt.setPaymentId(payment_id);
			bt.setPaymentStatus(payment_status);
			bt.setReference_id(f.getTransactionId());
			bt.setPaidAmount(f.getAmount().longValue());
			bt.setPaymentStatus(f.getPayments().get(0).getStatus().toUpperCase());
			bt.setPayment_mode("ONLINE");
			bt.getBooking().setPaymentStatus(payment_status.toUpperCase());
			bt.getBooking().setBookingStatus("PAID");
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

		Optional<Booking> bookings = bookingRepository.findById(booking.getId());
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


	public ApiResponse updateBooking(BookingDTO request) throws Exception {
		Optional<Booking> bookings = bookingRepository.findById(request.getId());
		if (!bookings.isPresent()) {
			return new ApiResponse(674, "Booking Not Found");
		}
		Booking booking = bookings.get();
		if (request.getBooking_price() != null)
			booking.setBooking_price(request.getBooking_price());

		if (request.getDel_ind() != null)
			booking.setDel_ind(request.getDel_ind());

		if (request.getBookingStatus() != null)
			booking.setBookingStatus(request.getBookingStatus());

		if (request.getCheckinDate() != null)
			booking.setCheckinDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getCheckinDate()));

		if (request.getCheckinStatus() != null)
			booking.setCheckinStatus(request.getCheckinStatus());

		if (request.getCheckoutDate() != null)
			booking.setCheckoutDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getCheckoutDate()));

		if (request.getCheckoutStatus() != null)
			booking.setCheckoutStatus(request.getCheckoutStatus());

		if (request.getCoupon_discount() != null)
			booking.setCoupon_discount(request.getCoupon_discount());

		if (request.getDiscount_price() != null)
			booking.setDiscount_price(request.getDiscount_price());

		if (request.getGst() != null)
			booking.setGst(request.getGst());

		if (request.getNoOfGuests() != 0)
			booking.setNoOfGuests(request.getNoOfGuests());

		if (request.getPaymentStatus() != null)
			booking.setPaymentStatus(request.getPaymentStatus());

		if (request.getRooms() != null) {
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

		return new ApiResponse(674, "SUCCESS");

	}

	public ApiResponse mapStaffTOHotel(MapStaffRequest request) {
		String hotelId = request.getHotelId();
		String[] staff = request.getStaff();
		Optional<Hotel> hotels = hotelRepository.findById(Long.valueOf(hotelId));
		if (!hotels.isPresent()) {
			return new ApiResponse(674, "Hotel Not found");
		}
		Hotel hotel = hotels.get();
		Set<User> userSet = new HashSet<>();
		// check user
		for (String id : staff) {
			Optional<User> users = userRepository.findById(Long.valueOf(id));
			if (!users.isPresent()) {
				return new ApiResponse(674, "user ID '" + id + "' is not present.");
			}
			User user = users.get();
			user.getHotels().add(hotel);
		}
		hotelRepository.save(hotel);
		return new ApiResponse(674, "SUCCESS");

	}

	public ApiResponse removeStafffromHotel(MapStaffRequest request) {
		String hotelId = request.getHotelId();
		String[] staff = request.getStaff();
		Optional<Hotel> hotels = hotelRepository.findById(Long.valueOf(hotelId));
		if (!hotels.isPresent()) {
			return new ApiResponse(674, "Hotel Not found");
		}
		Hotel hotel = hotels.get();
		// check user
		for (String id : staff) {
			Optional<User> users = userRepository.findById(Long.valueOf(id));
			if (!users.isPresent()) {
				return new ApiResponse(674, "user ID '" + id + "' is not present.");
			}
			User user = users.get();
			user.getHotels().remove(hotel);
		}
		hotelRepository.save(hotel);
		return new ApiResponse(674, "SUCCESS");

	}

	public ApiResponse addNewCoupon(CouponsRequest request) {
		if (request.getId() != null) {
			Optional<Coupon> coupons = couponRepository.findById(request.getId());
			if (!coupons.isPresent()) {
				return new ApiResponse(674, "coupon not existed");
			} else {
				Coupon coupon = coupons.get();

				coupon.setCode(request.getCode());
				coupon.setExpiry(request.getExpiry());
				coupon.setValue(request.getValue());
				coupon.setEnabled(request.getEnabled());

				couponRepository.save(coupon);
				return new ApiResponse(674, "SUCCESS");
			}
		} else {
			Date date = request.getExpiry();
			Coupon coupon = new Coupon(request.getCode(), request.getValue(), false, date);
			try {
				couponRepository.save(coupon);
			} catch (Exception e) {
				// TODO: handle exception
				return new ApiResponse(674, e.getMessage());
			}
			return new ApiResponse(674, "SUCCESS");
		}

	}

	public ApiResponse getAllBookings(String hotelId) {
		Optional<Hotel> hotels = hotelRepository.findById(Long.valueOf(hotelId));
		if (!hotels.isPresent()) {
			return new ApiResponse(674, "Hotel Not Found");
		}
		List<Booking> bookings = bookingRepository.finByHotel(Long.valueOf(hotelId));
		return new ApiResponse(674, "SUCCESS", bookings);
	}

	public ApiResponse getAllBookings() {
		Iterable<Booking> hotels = bookingRepository.findAll();

		return new ApiResponse(674, "SUCCESS", hotels);
	}

	public ApiResponse findBookingById(String id) {
		Optional<Booking> bookings = bookingRepository.findById(Long.valueOf(id));
		if (!bookings.isPresent()) {
			return new ApiResponse(674, "Booking Not Found");
		}
		Booking booking = bookings.get();
		return new ApiResponse(674, "SUCCESS", booking);
	}

	public ApiResponse initiateRefund(RefundRequest request) {
		Optional<Booking> bookings = bookingRepository.findById(Long.valueOf(request.getBookingId()));
		if (!bookings.isPresent()) {
			return new ApiResponse(674, "Booking Not Found");
		}
		BookingTransaction bt = bookings.get().getTransaction();
		if (bt == null) {
			return new ApiResponse(674, "Payment not initiated yet");
		}
		String paymentId = bt.getPaymentId();

		/*
		 * Create a new refund
		 */
		Refund refund = new Refund();
		refund.setPaymentId(paymentId);
		refund.setStatus("refunded");
		refund.setType("RFD");
		refund.setBody("Order Id:" + bt.getOrder_id());
		refund.setRefundAmount(request.getRefundAmount());

		try {
			Refund createdRefund = paymentService.getPaymentApi().createRefund(refund);
			Refunds ref = new Refunds(createdRefund.getId(), createdRefund.getPaymentId(), createdRefund.getStatus(), createdRefund.getTotalAmount(), createdRefund.getRefundAmount(), createdRefund.getCreatedAt(), bookings.get().getId());
			refundsRepository.save(ref);
			return new ApiResponse(432, "SUCCESS", createdRefund);

		} catch (HTTPException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
			System.out.println(e.getJsonPayload());
			return new ApiResponse(432, "FAILED", e.getJsonPayload());

		} catch (ConnectionException e) {
			System.out.println(e.getMessage());
		}
		return new ApiResponse(509, "FAILED");

	}

	
	// Booking can be cancelled upto 24 hrs
	public ApiResponse cancelBooking(BookingDTO request) {

		Optional<Booking> bookings = bookingRepository.findById(request.getId());
		if (!bookings.isPresent()) {
			return new ApiResponse(674, "Booking Not Found");
		}
		Booking booking = bookings.get();
		if(booking.getBookingStatus().equals("CANCELLED")) {
			return new ApiResponse(674, "BOOKING ALREADY CANCELLED");
		}
		Date checkinDate = booking.getCheckinDate();
		long checkinDateMillis = checkinDate.getTime();
		long offsetCancellationTime = 24 * 60 * 60 * 1000; // 24 hrs
		long throttleTime = checkinDateMillis - offsetCancellationTime;
		long currentTime = System.currentTimeMillis();
		if (currentTime > throttleTime) {
			return new ApiResponse(509,
					"Booking Can be cancelled Only Uptu 24 Hrs Of chekin Date,Please Contact Customer care");
		}
		BookingTransaction btr  = booking.getTransaction();
		if(btr == null) {

			booking.setBookingStatus("CANCELLED");
			booking.setPaymentStatus("CANCELLED");
			bookingRepository.save(booking);
			//dont change status code
			return new ApiResponse(200, "BOOKING CANCELLED: NO REFUND NEEDED");
		
		}
		PaymentOrder r= paymentService.getPaymentByOrderId(booking.getTransaction().getOrder_id());
		List<Payment> ps= r.getPayments();
		Payment payment =null;
		for(Payment p:ps) {
			if(p.getStatus() !=null) {
				payment = p;
			}
		}
		if (null != payment && payment.getStatus().equals("successful")) {
			// perform refund
			RefundRequest req = new RefundRequest(booking.getId(),
			Double.valueOf(booking.getTransaction().getPaidAmount()));
			ApiResponse resp = initiateRefund(req);
			if (resp.getMessage().equals("SUCCESS")) {
				booking.setBookingStatus("CANCELLED");
				booking.setPaymentStatus("REFUNDED");
				booking.getTransaction().setPaymentStatus("REFUNDED");
				bookingRepository.save(booking);
				return new ApiResponse(200, "BOOKING CANCELLED AND REFUND INITIATED",resp);
			} else {
				return new ApiResponse(674, "ERROR OCCURED WHILE BOOKING CANCELLATION");
			}

		} else {
			booking.setBookingStatus("CANCELLED");
			booking.setPaymentStatus("CANCELLED");
			bookingRepository.save(booking);
			return new ApiResponse(200, "BOOKING CANCELLED: NO REFUND NEEDED");
		}

	}

}
