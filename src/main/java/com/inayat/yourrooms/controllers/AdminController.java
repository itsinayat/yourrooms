	package com.inayat.yourrooms.controllers;

import java.io.IOException;

import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inayat.yourrooms.dto.BookingDTO;
import com.inayat.yourrooms.dto.BookingTransactionDTO;
import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.entity.Bookings;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.service.AdminService;
import com.inayat.yourrooms.service.HotelService;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	/*
	 * @RequestMapping(value = "/book", method = RequestMethod.POST) public
	 * ResponseEntity<ApiResponse> BookRoom(@RequestBody BookingDTO request) throws
	 * IOException { ApiResponse resp = hotelservice.BookRoom(request); return new
	 * ResponseEntity<>(resp, HttpStatus.OK); }
	 */
	
	@RequestMapping(value = "/getMyHotels", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getMyHotels() throws NumberFormatException, IOException {
		ApiResponse resp = adminService.getMyHotels();
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	
	
	
	
	
}
