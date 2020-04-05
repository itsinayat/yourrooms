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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.inayat.yourrooms.dao.UserDao;
import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.security.TokenHandler;
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

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> login(@RequestBody UsersDTO user) {
		ApiResponse response = userService.login(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private boolean logout(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp)
			throws JsonGenerationException, JsonMappingException, IOException {
		User loggedInUser = userRepository.findByUsername(user.getMobile());
		userDao.deactivateUserToken(loggedInUser, req.getHeader("Authorization"), resp);
		return true;
	}

	@RequestMapping(value = "/testAuth", method = RequestMethod.GET)
	public String test() {
		return "Running";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> register(@RequestBody UsersDTO user) {
		user.setPassword(encoder.encode(user.getPassword()));
		ApiResponse resp = userService.register(user);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/generate_otp", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> generate_otp(@RequestBody UsersDTO user) {
		ApiResponse response = userService.sendOTP(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/mobile-login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> loginMobile(@RequestBody UsersDTO user) {
		ApiResponse response = userService.login_mobile(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-profile", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse> getProfile() {
		ApiResponse response = userService.getProfile();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@RequestMapping(value = "/update-profile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateProfile(@RequestBody UsersDTO user) {
		ApiResponse response = userService.updateProfile(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	

}
