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

	public ApiResponse register(UsersDTO dto) {
		User user = UsersTranslator.convertToDao(dto);
		User u = userRepository.findByUsername(user.getUsername());
		if (u == null) {
			Optional<Role> r = roleRepository.findById(1L);
			user.setRole(r.get());
			Wallet wallet =new Wallet();
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
		if(user.getUsername()!=null && !user.getUsername().isEmpty()) {
			return new ApiResponse(50, "Username Cannot be set");
		}

		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user1 = userRepository.findByUsername(ud.getUsername());
		if(user.getMobile() !=null && !user1.getMobile().equals(user.getMobile())) {
			return new ApiResponse(50, "Mobile Number Cannot be set");
		}
		user1.setDob(user.getDob());
		user1.setEmail(user.getEmail());
		user1.setFirstName(user.getFirstName());
		user1.setGender(user.getGender());
		user1.setIs_enabled(user.getIs_enabled());
		user1.setIs_verified(user.getIs_verified());
		user1.setLastName(user.getLastName());
		user1.setMobile(user.getMobile());
		user1.setPassword(user.getPassword());
		userRepository.save(user1);
		return new ApiResponse(50, "SUCCESS");
	}

}
