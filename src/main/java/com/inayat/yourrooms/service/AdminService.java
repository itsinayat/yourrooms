package com.inayat.yourrooms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.UserRepository;

@Service
public class AdminService {
	
@Autowired
UserService userService;

@Autowired
UserRepository userRepository;


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
	

}
