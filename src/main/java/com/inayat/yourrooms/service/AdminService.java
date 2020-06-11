package com.inayat.yourrooms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.BookingTransactionRepository;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.UserRepository;

@Service
public class AdminService {
	
@Autowired
UserService userService;

@Autowired
UserRepository userRepository;


@Autowired
BookingTransactionRepository bookingTransactionRepository;

@Autowired
HotelRepository hotelRepository;


	public ApiResponse getMyHotels() {
		User user = userService.getCurrentUser();
		return new ApiResponse(7689, "SUCCESS",user.getHotels());
	}
	
	public ApiResponse findUserById(String id) {
		Optional<User> s = userRepository.findById(Long.valueOf(id));
		if(!s.isPresent()) {
			return new ApiResponse(321, "user Not found");
		}
		return new ApiResponse(321, "SUCCESS", s.get());
	}

	public ApiResponse viewTransactionById(String id) {
		Optional<BookingTransaction> s = bookingTransactionRepository.findById(Long.valueOf(id));
		if(!s.isPresent()) {
			return new ApiResponse(321, "TRXN Not found");
		}
		return new ApiResponse(321, "SUCCESS", s.get());
	
	}

	public ApiResponse findHotel(String id) {
		Optional<Hotel> hotels = hotelRepository.findById(Long.valueOf(id));
		if(hotels.isPresent()) {
			return new ApiResponse(321, "SUCCESS",hotels.get());
		}else {
			return new ApiResponse(321, "HOTEL NOT FOUND");
		}
	}

	public ApiResponse getAllUsers() {
		Iterable<User> users = userRepository.findAll();
		return new ApiResponse(321, "HOTEL NOT FOUND",users);
	}
	

}
