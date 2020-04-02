package com.inayat.yourrooms.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

/**
 * @author shrisowdhaman Dec 15, 2017
 */
@Service
public class OtpService {
//cache based on username and OPT MAX 8 
	private static final Integer EXPIRE_MINS = 5;
	private LoadingCache<String, String> otpCache;

	public OtpService() {
		super();
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return null;
					}
				});
	}

//This method is used to push the opt number against Key. Rewrite the OTP if it exists
	// Using user id as key
	public String generateOTP(String key) {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		String otp = String.format("%06d", number);
		otpCache.put(key, otp);
		return otp;
	}

	// This method is used to return the OPT number against Key->Key values is
	// username
	public String getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (Exception e) {
			return null;
		}
	}

//This method is used to clear the OTP catched already
	public void clearOTP(String key) {
		otpCache.invalidate(key);
	}
}