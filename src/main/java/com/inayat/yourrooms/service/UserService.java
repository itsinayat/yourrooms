package com.inayat.yourrooms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.dao.UserDao;
import com.inayat.yourrooms.dto.UserTokenDTO;
import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.entity.UserToken;
import com.inayat.yourrooms.entity.Wallet;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.repositories.WalletRepository;
import com.inayat.yourrooms.security.TokenHandler;
import com.inayat.yourrooms.translator.UserTokenTranslator;
import com.inayat.yourrooms.translator.UsersTranslator;
import com.inayat.yourrooms.utils.Constants;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WalletRepository walletRepository;
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
	private static final String  defaultPassoword = "password";

	public ApiResponse register(UsersDTO dto) {
		User user = UsersTranslator.convertToDao(dto);
		User u = userRepository.findByUsername(user.getUsername());
		if (u == null) {
			Optional<Role> r = roleRepository.findById(1L);
			user.setRole(r.get());
			Wallet wallet = new Wallet();
			wallet.setBalance(0L);
			wallet.setDel_ind(false);
			wallet.setIs_activated(false);
			Wallet newwallet = walletRepository.save(wallet);
			user.setWallet(newwallet);
			userRepository.save(user);
			return new ApiResponse(41, "SUCCESS");
		} else {
			return new ApiResponse(42, "User Already Registered, Please Login.");
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
		return new ApiResponse(43, "SUCCESS", dto);

	}

	public ApiResponse loginWithOtp(UsersDTO user) {
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
			return new ApiResponse(44, "SUCCESS", dto);
		} else {
			return new ApiResponse(45, "Wrong OTP");
		}

	}

	public ApiResponse sendOTP(UsersDTO dto) {

		User user = userRepository.findByUsername(dto.getMobile());
		if (user != null) {
			String mobile = user.getMobile();
			String otp = otpService.generateOTP(mobile);
			Boolean result = sendMessage(mobile, otp);
			if (result) {
				return new ApiResponse(46, "SUCCESS", otp);
			} else {
				return new ApiResponse(47, "OTP could not be send");
			}
		} else {
			return new ApiResponse(48, "User Not found");
		}

	}

	public Boolean sendMessage(String mobile, String otp) {
		return true;
	}

	public ApiResponse getProfile() {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsername(ud.getUsername());
		UsersDTO dto = UsersTranslator.convertToDto(user);
		return new ApiResponse(49, "SUCCESS", dto);
	}

	public ApiResponse updateProfile(UsersDTO user) {
		if (user.getUsername() != null && !user.getUsername().isEmpty()) {
			return new ApiResponse(50, "Username Cannot be set");
		}

		if (user.getMobile() != null && !user.getMobile().isEmpty()) {
			return new ApiResponse(50, "Mobile Cannot be set");
		}

		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			return new ApiResponse(50, "Password Cannot be set directly");
		}

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dao = userRepository.findByUsername(ud.getUsername());
		dao.setDob(user.getDob());
		dao.setEmail(user.getEmail());
		dao.setFirstName(user.getFirstName());
		dao.setGender(user.getGender());
		dao.setIs_enabled(user.getIs_enabled());
		dao.setIs_verified(user.getIs_verified());
		dao.setLastName(user.getLastName());
		userRepository.save(dao);
		return new ApiResponse(50, "SUCCESS");
	}

	public ApiResponse registerByOtp(UsersDTO dto) {
		if (dto.getOtp().equals(otpService.getOtp(dto.getMobile()))) {
			User u = userRepository.findByUsername(dto.getMobile());
			if (u == null) {
				User user = new User();
				user.setMobile(dto.getMobile());
				user.setUsername(dto.getMobile());
				user.setPassword(defaultPassoword);
				Optional<Role> r = roleRepository.findById(1L);
				user.setRole(r.get());
				Wallet wallet = new Wallet();
				wallet.setBalance(0L);
				wallet.setDel_ind(false);
				wallet.setIs_activated(false);
				Wallet newwallet = walletRepository.save(wallet);
				user.setWallet(newwallet);
				userRepository.save(user);
				return new ApiResponse(51, "User Registered Successfully.");
			} else {
				return new ApiResponse(42, "User Already Registered, Please Login.");
			}

		} else {
			return new ApiResponse(45, "Wrong OTP");
		}
	}

	public ApiResponse generateOtp(UsersDTO dto) {
		String mobile = dto.getMobile();
		String otp = otpService.generateOTP(mobile);
		Boolean result = sendMessage(mobile, otp);
		if (result) {
			return new ApiResponse(46, "SUCCESS", otp);
		} else {
			return new ApiResponse(47, "OTP could not be send");
		}
	}

}
