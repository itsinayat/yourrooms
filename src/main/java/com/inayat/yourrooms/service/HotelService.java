package com.inayat.yourrooms.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inayat.yourrooms.dto.BookingDTO;
import com.inayat.yourrooms.dto.BookingResponse;
import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Bookings;
import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Coupons;
import com.inayat.yourrooms.entity.Hotels;
import com.inayat.yourrooms.entity.Rooms;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.BookingRepository;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.CouponRepository;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.RoomsRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.translator.BookingResponseTranslator;
import com.inayat.yourrooms.translator.RoomsTranslator;

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

			}
			return new ApiResponse(543, "SUCCESS");

		}
	}

	public ApiResponse getAllHotel(String city, String pincode) {
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
			return new ApiResponse(343, "SUCCESS", hotellist);

		}
	}

	public ApiResponse addRoomsTOHotel(RoomsDTO dto) {
		Optional<Hotels> h = hotelRepository.findById(dto.getHotelId());

		if (!h.isPresent()) {
			return new ApiResponse(413, "Hotel not found");
		}
		Hotels hotel = h.get();
		Rooms room = RoomsTranslator.translateToDao(dto, hotel);
		roomsRepository.save(room);
		return new ApiResponse(432, "SUCCESS");

	}

	public ApiResponse getAllHotelRooms(Long hotel_id) {
		Hotels hotel = hotelRepository.findById(hotel_id).get();
		if (hotel == null) {
			return new ApiResponse(413, "Hotel not found");
		}
		List<Rooms> rooms = roomsRepository.findAllByHotel(hotel);
		return new ApiResponse(322, "SUCCESS", rooms);
	}

	public ApiResponse getRoom(Long room_id) {
		Rooms rooms = roomsRepository.findById(room_id).get();
		if (rooms == null) {
			return new ApiResponse(413, "Room not found");
		}
		return new ApiResponse(322, "SUCCESS", RoomsTranslator.translateToDTO(rooms));
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
}
