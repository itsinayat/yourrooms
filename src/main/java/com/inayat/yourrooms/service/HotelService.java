package com.inayat.yourrooms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.entity.Hotels;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.HotelRepository;

@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private UserService userService;

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
		if (city != null && pincode ==null) {
			List<Hotels> list = hotelRepository.findByCity(city);
			return new ApiResponse(343, "SUCCESS", list);
		} else if (pincode != null && city == null) {
			List<Hotels> list = hotelRepository.findByPinCode(pincode);
			return new ApiResponse(343, "SUCCESS", list);
		} 
		else if (pincode != null && city != null) {
			List<Hotels> list = hotelRepository.findByPinCodeAndCity(pincode,city);
			return new ApiResponse(343, "SUCCESS", list);
		}
		else {
			Iterable<Hotels> hotellist = hotelRepository.findAll();
			return new ApiResponse(343, "SUCCESS", hotellist);

		}
	}
}
