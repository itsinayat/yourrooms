package com.inayat.yourrooms.service;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.dao.UserDao;
import com.inayat.yourrooms.dto.BookingHistoryResponse;
import com.inayat.yourrooms.dto.ReviewAndRatingsDTO;
import com.inayat.yourrooms.dto.UserTokenDTO;
import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.dto.WalletDTO;
import com.inayat.yourrooms.dto.WalletTransactionHistoryResponse;
import com.inayat.yourrooms.entity.Booking;
import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.ReviewAndRating;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.entity.UserToken;
import com.inayat.yourrooms.entity.Wallet;
import com.inayat.yourrooms.entity.WalletTransaction;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.BookingRepository;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.ReviewAndRatingsRepository;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.repositories.WalletRepository;
import com.inayat.yourrooms.repositories.WalletTransactionRepository;
import com.inayat.yourrooms.security.TokenHandler;
import com.inayat.yourrooms.translator.BookingHistoryResponseTranslator;
import com.inayat.yourrooms.translator.UserTokenTranslator;
import com.inayat.yourrooms.translator.UsersTranslator;
import com.inayat.yourrooms.translator.WalletTransactionHistoryResponseTranslator;
import com.inayat.yourrooms.translator.WalletTranslator;
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
	ConfigurationRepository configurationRepository;
	@Autowired
	WalletTransactionRepository walletTransactionRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	ReviewAndRatingsRepository reviewAndRatingsRepository;
	@Autowired
	UserDao userDao;
	@Autowired
	TokenHandler tokenHandler;
	@Autowired
	OtpService otpService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	HotelRepository hotelRepository;
	private static final String defaultPassoword = "password";

	public ApiResponse register(UsersDTO dto) {
		
		User u = userRepository.findByMobile(dto.getMobile());
		if (u == null) {
			User user = UsersTranslator.convertToDao(dto);
			user.setUsername(dto.getMobile());
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
		try {
		userRepository.save(dao);
		}catch (Exception e) {
			return new ApiResponse(50, e.getMessage());
		}
		return new ApiResponse(50, "SUCCESS");
	}

	public ApiResponse registerByOtp(UsersDTO dto) {
		if (dto.getOtp().equals(otpService.getOtp(dto.getMobile()))) {
			User u = userRepository.findByUsername(dto.getMobile());
			if (u == null) {
				User user = new User();
				user.setMobile(dto.getMobile());
				user.setUsername(dto.getMobile());
				user.setPassword(encoder.encode(defaultPassoword));
				user.setDel_ind(false);
				user.setCreate_user_id(0L);
				user.setIs_enabled(true);
				user.setIs_logged_in(false);
				user.setIs_verified(true);
				String referral_code = createReferalCode(6);
				user.setReferral_code(referral_code);
				Optional<Role> r = roleRepository.findById(1L);
				user.setRole(r.get());
				Wallet wallet = new Wallet();
				wallet.setBalance(0L);
				wallet.setDel_ind(false);
				wallet.setIs_activated(true);
				wallet.setCreate_user_id(0L);
				wallet.setUpdate_user_id(0L);
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

	public String createReferalCode(int codeLength) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new SecureRandom();
		for (int i = 0; i < codeLength; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		System.out.println(output);
		return output;
	}
	
	public String createReferenceID() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new SecureRandom();
		for (int i = 0; i < 16; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		System.out.println(output);
		return output;
	}

	public ApiResponse createReferral(UsersDTO dto) {
		String mobile = dto.getMobile();
		User user = userRepository.findByUsername(mobile);
		if (user == null) {
			return new ApiResponse(611, "User Not Found");
		}
		if(user.getReferred_by()!=null) {
			return new ApiResponse(611, "referral already set");
		}
		String refferralcode = dto.getReferral_code();
		User referralUser = userRepository.findByReferral_code(refferralcode);
		if (referralUser == null) {
			return new ApiResponse(61, "Invalid Referral Code");
		} else {
			if (user.getReferral_code().equals(refferralcode)) {
				return new ApiResponse(61, "Invalid Referral Code");
			}
			user.setReferred_by(referralUser.getId());
			Configuration c =configurationRepository.findByKey("REFERRAL_BONUS");
			user.getWallet().setBalance(Long.valueOf(c.getValue()));
			WalletTransaction tr = new WalletTransaction();
			tr.setAmount(Long.valueOf(c.getValue()));
			tr.setComment("Referral Bonus");
			tr.setReference_id(createReferenceID());
			tr.setTr_type("CREDIT");
			tr.setUpdate_user_id(0L);
			tr.setCreate_user_id(0L);
			tr.setWallet(user.getWallet());
			walletTransactionRepository.save(tr);
			userRepository.save(user);
		}
		return new ApiResponse(611, "Referral Saved");

	}
	public User getCurrentUser() {
		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User loggedInUser = userRepository.findByUsername(ud.getUsername());
			return loggedInUser;
		}else {
			return null;
		}
	}

	public ApiResponse getWallet() {
		Wallet wallet = getCurrentUser().getWallet();
		WalletDTO dto = WalletTranslator.convertToDto(wallet);
		return new ApiResponse(435, "success", dto);
		
	}

	public ApiResponse getWalletTransaction() {
		Wallet wallet = getCurrentUser().getWallet();
		List<WalletTransaction> trs = walletTransactionRepository.findByWallet(wallet);
		List<WalletTransactionHistoryResponse> list =WalletTransactionHistoryResponseTranslator.translate(trs);
		return new ApiResponse(321, "SUCCESS",list);
	}
	
	public ApiResponse getBookingHistory() {
		User user = getCurrentUser();
		List<Booking> bookings = bookingRepository.findByUser(user);
		List<BookingHistoryResponse> list = BookingHistoryResponseTranslator.translate(bookings);
		return new ApiResponse(321, "SUCCESS", list);
	}

	public ApiResponse setReviewAndRating(ReviewAndRatingsDTO dto) {
		Optional<Hotel> h = hotelRepository.findById(dto.getHotelId());
		if(!h.isPresent()) {
			return new ApiResponse(321, "Hotel Not found");
		}
		User user = getCurrentUser();
		Hotel hotel  =h.get();
		ReviewAndRating rr = reviewAndRatingsRepository.findByUserAndHotel(user.getId(), hotel);
		if(rr!=null) {
			rr.setComment(dto.getComment());
			rr.setDel_ind(false);
			rr.setHotel(hotel);
			rr.setRating(dto.getRating());
			rr.setUpdate_user_id(user.getId());
			rr.setCreate_user_id(user.getId());
			reviewAndRatingsRepository.save(rr);
			return new ApiResponse(321, "Updated Review");
		}else {
			rr = new ReviewAndRating();
			rr.setComment(dto.getComment());
			rr.setDel_ind(false);
			rr.setHotel(hotel);
			rr.setRating(dto.getRating());
			rr.setUpdate_user_id(user.getId());
			rr.setCreate_user_id(user.getId());
			reviewAndRatingsRepository.save(rr);
			return new ApiResponse(321, "created Review");
		}
		
		

		
	}
	
	
	

}
