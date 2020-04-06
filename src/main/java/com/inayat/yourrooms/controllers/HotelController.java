package com.inayat.yourrooms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.service.HotelService;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	HotelService hotelservice;

	@RequestMapping(value = "/add-or-update-hotel", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> addOrUpdate(@RequestBody HotelDTO hotel) {
		ApiResponse resp = hotelservice.addOrUpdate(hotel);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll-hotels", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getAllHotel(@RequestParam(required = false) String city,@RequestParam(required = false) String pincode) {
		ApiResponse resp = hotelservice.getAllHotel(city,pincode);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
