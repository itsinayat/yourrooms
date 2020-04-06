package com.inayat.yourrooms.service;

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
			dao.setCreate_user_id(u.getId());
			dao.setUpdate_user_id(u.getId());
			dao.setDel_ind(false);
			hotelRepository.save(dao);
			return new ApiResponse(543, "SUCCESS");
		} else {
			Optional<Hotels> h = hotelRepository.findById(hotel.getId());
			if (h.isPresent()) {
				Hotels dao = 	h.get();
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
				dao.setUpdate_user_id(u.getId());
				hotelRepository.save(dao);
				
			}
			return new ApiResponse(543, "SUCCESS");

		}
	}

}
