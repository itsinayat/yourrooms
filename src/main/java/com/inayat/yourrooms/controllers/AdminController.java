package com.inayat.yourrooms.controllers;

import java.io.IOException;

import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inayat.yourrooms.dto.BookingDTO;
import com.inayat.yourrooms.dto.BookingTransactionDTO;
import com.inayat.yourrooms.dto.ConfigurationRequest;
import com.inayat.yourrooms.dto.CouponsRequest;
import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.dto.MapStaffRequest;
import com.inayat.yourrooms.dto.ReviewAndRatingsDTO;
import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.entity.Booking;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.service.AdminService;
import com.inayat.yourrooms.service.FileStorageService;
import com.inayat.yourrooms.service.HotelService;
import com.inayat.yourrooms.service.UserService;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	AdminService adminService;

	@Autowired
	HotelService hotelservice;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/add-or-update-hotel", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> addOrUpdate(@RequestBody HotelDTO hotel) {
		ApiResponse resp = hotelservice.addOrUpdate(hotel);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-or-update-rooms-to-hotel", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> addRoomsTOHotel(@RequestBody RoomsDTO rooms) {
		ApiResponse resp = hotelservice.addRoomsTOHotel(rooms);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("/uploadMultipleFiles")
	public ResponseEntity<ApiResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("id") String id, @RequestParam("flag") String flag) throws IOException {
		ApiResponse res = null;
		if (flag.equalsIgnoreCase("room"))
			res = fileStorageService.storeFileRoomsMultiple(files, id);
		if (flag.equalsIgnoreCase("hotel"))
			res = fileStorageService.storeFileHotelsMultiple(files, id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/getMyHotels", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getMyHotels() throws NumberFormatException, IOException {
		ApiResponse resp = adminService.getMyHotels();
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping("/delete/{type}/{id}")
	public ResponseEntity<ApiResponse> DelateFile(@PathVariable("id") String id,
			@PathVariable("type") String type) {
		if (type.equalsIgnoreCase("room")) {
			ApiResponse response = fileStorageService.deletefromroom(id);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		else if (type.equalsIgnoreCase("hotel")) {
			ApiResponse response = fileStorageService.deletefromhotel(id);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new ApiResponse(657, "BAD REQUEST"), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/add-staff-to-hotel")
	public ResponseEntity<ApiResponse> mapStaff(@RequestBody MapStaffRequest request) {
		ApiResponse res = hotelservice.mapStaffTOHotel(request);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@PostMapping("/remove-staff-from-hotel")
	public ResponseEntity<ApiResponse> removeStaff(@RequestBody MapStaffRequest request) {
		ApiResponse res = hotelservice.removeStafffromHotel(request);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@PostMapping("/addUpdateNewCoupon")
	public ResponseEntity<ApiResponse> addNewCoupon(@RequestBody CouponsRequest request) {
		ApiResponse res = hotelservice.addNewCoupon(request);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}
	
	@GetMapping("/getAllBookings/{hotelId}")
	public ResponseEntity<ApiResponse> getAllBookings(@PathVariable("hotelId") String hotelId) {
		ApiResponse res = hotelservice.getAllBookings(hotelId);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}
	
	@GetMapping("/getAllBookings")
	public ResponseEntity<ApiResponse> getAllBooking() {
		ApiResponse res = hotelservice.getAllBookings();
		return new ResponseEntity<>(res, HttpStatus.OK);

	}
	
	@GetMapping("/findBookingById/{id}")
	public ResponseEntity<ApiResponse> findBookingById(@PathVariable String id) {
		ApiResponse res = hotelservice.findBookingById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/findUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> findUserById(@PathVariable String id) {
		ApiResponse response = adminService.findUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/viewTransactionById/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> viewTransactionById(@PathVariable String id) {
		ApiResponse response = adminService.viewTransactionById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/findHotel/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> findHotel(@PathVariable String id) {
		ApiResponse response = adminService.findHotel(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getCouponById/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getCouponById(@PathVariable String id) {
		ApiResponse response = adminService.getCouponById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getAllUsers() {
		ApiResponse response = adminService.getAllUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/deleteCoupon/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> deleteCoupon(@PathVariable("id") String id) {
		ApiResponse response = adminService.deleteCoupon(id);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getRevenue", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getRevenue() {
		ApiResponse response = adminService.getRevenue();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateProfileById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateProfileById(@RequestBody UsersDTO user) {
		ApiResponse response = userService.updateProfileById(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllRefunds", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getAllRefunds() {
		ApiResponse response = adminService.getAllRefunds();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getAllConfiguration", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getAllConfiguration() {
		ApiResponse response = adminService.getAllConfiguration();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateConfig", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> updateConfig(@RequestBody ConfigurationRequest request) {
		ApiResponse response = adminService.updateConfig(request);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/addNewConfig", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> addNewConfig(@RequestBody ConfigurationRequest request) {
		ApiResponse response = adminService.addNewConfig(request);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/deleteConfig/{key}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> deleteConfig(@PathVariable("key") String key) {
		ApiResponse response = adminService.deleteConfig(key);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateReview", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> updateReview(@RequestBody ReviewAndRatingsDTO dto) {
		ApiResponse response = adminService.updateReview(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/searchUser/{mobile}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> findUserByMobile(@PathVariable("mobile") String mobile) {
		ApiResponse response = userService.findUserByMobile(mobile);
		System.out.println(mobile);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
