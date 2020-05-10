package com.inayat.yourrooms.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import com.inayat.yourrooms.dto.CouponsRequest;
import com.inayat.yourrooms.dto.HotelDTO;
import com.inayat.yourrooms.dto.MapStaffRequest;
import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.service.FileStorageService;
import com.inayat.yourrooms.service.HotelService;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	HotelService hotelservice;

	@Autowired
	FileStorageService fileStorageService;


	@RequestMapping(value = "/getAll-hotels", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getAllHotel(@RequestParam(required = false) String city,
			@RequestParam(required = false) String pincode) throws JsonProcessingException {
		ApiResponse resp = hotelservice.getAllHotel(city, pincode);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}



	@RequestMapping(value = "/getAll-rooms", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getAllHotelRooms(@RequestParam("hotel_id") Long hotel_id) {
		ApiResponse resp = hotelservice.getAllHotelRooms(hotel_id);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRoom", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getRoom(@RequestParam("id") Long id) {
		ApiResponse resp = hotelservice.getRoom(id);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> BookRoom(@RequestBody BookingDTO request) throws IOException {
		ApiResponse resp = hotelservice.BookRoom(request);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-booking", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> updateBooking(@RequestBody BookingDTO request) throws Exception {
		ApiResponse resp = hotelservice.updateBooking(request);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/apply-coupon/{bookingId}/{code}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> applyCoupon(@PathVariable("code") String code,
			@PathVariable("bookingId") String bookingId) throws NumberFormatException, IOException {
		ApiResponse resp = hotelservice.applyCoupon(code, Long.parseLong(bookingId));
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/cancelBooking/{bookingId}", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> cancelBooking(@PathVariable("bookingId") String bookingId) throws IOException {
		ApiResponse resp = hotelservice.cancelBooking(bookingId);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	

	@GetMapping("/downloadFile/{type}/{roomId}/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName,
			@PathVariable("roomId") String roomId, @PathVariable("type") String type, HttpServletRequest request) {
		// Load file as Resource
		Resource resource;
		if (type.equalsIgnoreCase("room")) {
			resource = fileStorageService.loadFileAsResourceRoom(fileName, roomId);
		}

		else if (type.equalsIgnoreCase("hotel")) {
			resource = fileStorageService.loadFileAsResourceHotel(fileName, roomId);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {

		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}


}
