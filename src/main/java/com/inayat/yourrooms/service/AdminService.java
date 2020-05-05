package com.inayat.yourrooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;

@Service
public class AdminService {
	
@Autowired
UserService userService;

	public ApiResponse getMyHotels() {
		User user = userService.getCurrentUser();
		return new ApiResponse(7689, "SUCCESS",user.getHotels());
	}

}
