package com.inayat.yourrooms.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.dto.ConfigurationRequest;
import com.inayat.yourrooms.entity.Booking;
import com.inayat.yourrooms.entity.BookingTransaction;
import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Coupon;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.Refunds;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.BookingRepository;
import com.inayat.yourrooms.repositories.BookingTransactionRepository;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.CouponRepository;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.RefundsRepository;
import com.inayat.yourrooms.repositories.UserRepository;

@Service
public class AdminService {
	
@Autowired
UserService userService;

@Autowired
UserRepository userRepository;


@Autowired
BookingTransactionRepository bookingTransactionRepository;

@Autowired
HotelRepository hotelRepository;


@Autowired
BookingRepository bookingRepository;


@Autowired
CouponRepository couponRepository;

@Autowired
RefundsRepository refundsRepository;


@Autowired
ConfigurationRepository configurationRepository;




	public ApiResponse getMyHotels() {
		User user = userService.getCurrentUser();
		return new ApiResponse(7689, "SUCCESS",user.getHotels());
	}
	
	public ApiResponse findUserById(String id) {
		Optional<User> s = userRepository.findById(Long.valueOf(id));
		if(!s.isPresent()) {
			return new ApiResponse(321, "user Not found");
		}
		return new ApiResponse(321, "SUCCESS", s.get());
	}

	public ApiResponse viewTransactionById(String id) {
		Optional<BookingTransaction> s = bookingTransactionRepository.findById(Long.valueOf(id));
		if(!s.isPresent()) {
			return new ApiResponse(321, "TRXN Not found");
		}
		return new ApiResponse(321, "SUCCESS", s.get());
	
	}

	public ApiResponse findHotel(String id) {
		Optional<Hotel> hotels = hotelRepository.findById(Long.valueOf(id));
		if(hotels.isPresent()) {
			return new ApiResponse(321, "SUCCESS",hotels.get());
		}else {
			return new ApiResponse(321, "HOTEL NOT FOUND");
		}
	}

	public ApiResponse getAllUsers() {
		Iterable<User> users = userRepository.findAll();
		return new ApiResponse(321, "SUCCESS",users);
	}

	public ApiResponse getCouponById(String id) {
		Optional<Coupon> coupons = couponRepository.findById(Long.valueOf(id));
		if(!coupons.isPresent()) {
			return new ApiResponse(321, "TRXN Not found");
		}
		return new ApiResponse(321, "SUCCESS", coupons.get());
	}

	public ApiResponse deleteCoupon(String id) {
		try {
		couponRepository.deleteById(Long.valueOf(id));
		}catch (Exception e) {
			return new ApiResponse(321, "COUPON NOT FOUND");
		}
		return new ApiResponse(321, "SUCCESS");
	}

	public ApiResponse getRevenue() {
		Iterable<Booking> hotels = bookingRepository.findAll();
		Iterator<Booking> it = hotels.iterator();
		double sum=0;
		while(it.hasNext()) {
			Booking booking=  it.next();
			double x=  booking.getBooking_price() -booking.getCoupon_discount() -booking.getDiscount_price();
			sum=sum+x;
		}
		return new ApiResponse(674, "SUCCESS", sum);
	}

	public ApiResponse getAllRefunds() {
		Iterable<Refunds> rfnds = refundsRepository.findAll();
		return new ApiResponse(321, "SUCCESS",rfnds);
	
	}

	public ApiResponse getAllConfiguration() {
		Iterable<Configuration> confs = 	configurationRepository.findAll();
		return new ApiResponse(321, "SUCCESS",confs);
	}

	public ApiResponse updateConfig(ConfigurationRequest request) {
		Configuration config = configurationRepository.findByKey(request.getKey());
		if(config == null) {
			return new ApiResponse(321, "NO KEY FOUND");
		}
		config.setValue(request.getValue());
		configurationRepository.save(config);
		return new ApiResponse(321, "SUCCESS");
	}

	public ApiResponse addNewConfig(ConfigurationRequest request) {
		Configuration config =new  Configuration(request.getKey(),request.getValue());
		configurationRepository.save(config);
		return new ApiResponse(321, "SUCCESS");
	}

	public ApiResponse deleteConfig(String key) {
		Configuration config = configurationRepository.findByKey(key);
		if(config == null) {
			return new ApiResponse(321, "KEY NOT FOUND");
		}
		configurationRepository.deleteByKey(key);
		return new ApiResponse(321, "SUCCESS");
	}
	
	

}
