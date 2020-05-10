package com.inayat.yourrooms.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.inayat.yourrooms.dao.UserDao;
import com.inayat.yourrooms.dto.ReviewAndRatingsDTO;
import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.entity.Coupon;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.CouponRepository;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.security.TokenHandler;
import com.inayat.yourrooms.service.FileStorageService;
import com.inayat.yourrooms.service.UserService;

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping("/user")
public class AuthController {

	@Autowired
	UserDao userDao;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	TokenHandler tokenHandler;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserService userService;

	@Autowired
	CouponRepository couponRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> login(@RequestBody UsersDTO user) {
		ApiResponse response = userService.login(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private ResponseEntity<ApiResponse> logout( HttpServletRequest req, HttpServletResponse resp)
			throws JsonGenerationException, JsonMappingException, IOException {
		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User loggedInUser = userRepository.findByUsername(ud.getUsername());
			userDao.deactivateUserToken(loggedInUser, req.getHeader("Authorization"), resp);
			return new ResponseEntity<>(new ApiResponse(340, "Logged out Success"), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ApiResponse(340, "Already Logged out"), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> register(@RequestBody UsersDTO user) {
		user.setPassword(encoder.encode(user.getPassword()));
		ApiResponse resp = userService.register(user);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/testAuth", method = RequestMethod.GET)
	public String test() {
		return "Running";
	}

	@RequestMapping(value = "/generate-otp", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> generateOtpForRegistration(@RequestBody UsersDTO user) {
		ApiResponse response = userService.generateOtp(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/register-by-otp", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> mobileRegister(@RequestBody UsersDTO user) {
		ApiResponse resp = userService.registerByOtp(user);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/login-by-otp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> loginWithOtp(@RequestBody UsersDTO user) {
		ApiResponse response = userService.loginWithOtp(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-profile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateProfile(@RequestBody UsersDTO user) {
		ApiResponse response = userService.updateProfile(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-profile", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getProfile() {
		ApiResponse response = userService.getProfile();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/set-referral", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> setReferral(@RequestBody UsersDTO dto) {
		ApiResponse response = userService.createReferral(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-wallet", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getWallet() {
		ApiResponse response = userService.getWallet();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-wallet-ransaction", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getWalletTransaction() {
		ApiResponse response = userService.getWalletTransaction();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-booking-history", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getBookinghistory() {
		ApiResponse response = userService.getBookingHistory();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-all-coupons", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getAllCoupons() {
		Iterable<Coupon> response = couponRepository.findAll();
		ApiResponse res = new ApiResponse(4234, "SUCCESS", response);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/review-and-rating", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> reviewAndRating(@RequestBody ReviewAndRatingsDTO dto) {
		ApiResponse response = userService.setReviewAndRating(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
