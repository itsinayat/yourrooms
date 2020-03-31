package com.inayat.yourrooms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inayat.yourrooms.dao.UserDao;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.entity.UserToken;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.security.TokenHandler;
import com.inayat.yourrooms.utils.Constants;

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
	

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserToken login(@RequestBody User user) {
		UserToken userToken = new UserToken();
		

		// Check user credentials
		Authentication authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		// Authenticate user in spring security context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Generate token
		User userDetails = userDao.getUser(user.getUsername());
		String tokenKey = this.tokenHandler.generateToken(userDetails);

		// Build response
		userToken.setUser(userDetails);
		userToken.setTokenKey(tokenKey);
		userToken.setStatus(Constants.UsetTokenStatus.ACTIVE);

		// Save token
		userDao.saveUserToken(userToken);

		return userToken;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private boolean logout() {
		User currentUser = (User) userDetailsService.loadUserByUsername(null);
		userDao.deactivateUserToken(currentUser);
		return true;
	}

	@RequestMapping(value = "/testAuth", method = RequestMethod.GET)
	public String test() {
		return "It works!";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		Optional<Role> r = roleRepository.findById(1L);
		user.setRole(r.get());
		userRepository.save(user);
		
		return "SUCCESS";
		
	} 
	

}
