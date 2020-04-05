package com.inayat.yourrooms.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.dao.UserDao;
import com.inayat.yourrooms.dto.UserTokenDTO;
import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.entity.UserToken;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.security.TokenHandler;
import com.inayat.yourrooms.translator.UserTokenTranslator;
import com.inayat.yourrooms.translator.UsersTranslator;
import com.inayat.yourrooms.utils.Constants;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDao userDao;
	@Autowired
	TokenHandler tokenHandler;
	@Autowired
	OtpService otpService;

	public ApiResponse register(UsersDTO dto) {
		User user = UsersTranslator.convertToDao(dto);
		User u = userRepository.findByUsername(user.getUsername());
		if (u == null) {
			Optional<Role> r = roleRepository.findById(1L);
			user.setRole(r.get());
			userRepository.save(user);
			return new ApiResponse(400, "SUCCESS");
		} else {
			return new ApiResponse(400, "User Already Registered, Please Login.");
		}

	}

	public ApiResponse login(UsersDTO user) {
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
		UserTokenDTO dto = UserTokenTranslator.translateToDTO(userToken);
		return new ApiResponse(200, "SUCCESS", dto);

	}

	public ApiResponse login_mobile(UsersDTO user) {
		if (user.getOtp().equals(otpService.getOtp(user.getMobile()))) {

			UserToken userToken = new UserToken();
			User u = userRepository.findByUsername(user.getMobile());
			// Generate token
			User userDetails = userDao.getUser(u.getUsername());
			String tokenKey = this.tokenHandler.generateToken(userDetails);

			// Build response
			userToken.setUser(userDetails);
			userToken.setTokenKey(tokenKey);
			userToken.setStatus(Constants.UsetTokenStatus.ACTIVE);

			// Save token
			userDao.saveUserToken(userToken);
			UserTokenDTO dto = UserTokenTranslator.translateToDTO(userToken);
			return new ApiResponse(200, "SUCCESS", dto);
		} else {
			return new ApiResponse(200, "Wrong OTP");
		}

	}

	public ApiResponse sendOTP(UsersDTO dto) {
		
		User user = userRepository.findByUsername(dto.getUsername());
		if (user != null) {
			String mobile = user.getMobile();
			String otp =otpService.generateOTP(mobile);
			Boolean result = sendMessage(mobile,otp);
			if (result) {
				return new ApiResponse(200, "SUCCESS", otp);
			} else {
				return new ApiResponse(400, "OTP could not be send");
			}
		} else {
			return new ApiResponse(400, "User Not found");
		}

	}

	public Boolean sendMessage(String mobile,String otp) {
		return true;
	}

	public ApiResponse updateProfile() {
		
		return new ApiResponse(349, "hello");
	}

}
