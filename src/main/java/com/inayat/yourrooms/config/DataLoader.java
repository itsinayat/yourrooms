package com.inayat.yourrooms.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Coupon;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.CouponRepository;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.utils.Utils;

import ch.qos.logback.classic.pattern.Util;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private CouponRepository couponRepository;

	public void run(ApplicationArguments args) {
		roleRepository.save(new Role(1L,"ROLE_ADMIN"));
		roleRepository.save(new Role(2L,"ROLE_CONSUMER"));
		roleRepository.save(new Role(3L,"ROLE_SUPERADMIN"));
		configurationRepository.save(new Configuration(1L,"REFERRAL_BONUS","100"));
		configurationRepository.save(new Configuration(2L,"GST_RATE","10"));
//		Coupon c =new Coupon(1L,"FREE50", Double.valueOf(50),true,Utils.addDays(new Date(), 30));
//		couponRepository.save(c);
		configurationRepository.save(new Configuration(3L,"IM_PRIVATE_KEY","test_c0fe7a31708c15d1d1fd548d88c"));
		configurationRepository.save(new Configuration(4L,"IM_AUTH_TOKEN","test_75914bf49a32ffaaee32bf441b8"));
		configurationRepository.save(new Configuration(5L,"IM_SALT","fb7d6875d700457198525b308bcd1d52"));
		configurationRepository.save(new Configuration(6L,"IM_CLIENT_SECRET","test_uICB6FvnzLkYR0lS256p7WIVUkxO9Ddm4uyzwXWuqVhSKbvdUVoOsLWkFHiW0w1VMlXVtjZ8WWXa3wSTHieGqGVxxbqhrTnkxrbWWiUPhT0rTHGnrcd200TAMnE"));
		configurationRepository.save(new Configuration(7L,"IM_CLIENT_ID","test_6I2TqQfGs3yXN2SH9yfgQJgL0sV4NvSNy1V"));
	}
}