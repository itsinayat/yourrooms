package com.inayat.yourrooms.config;

import java.util.Date;

import javax.ws.rs.Encoded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Coupon;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.CouponRepository;
import com.inayat.yourrooms.repositories.RoleRepository;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.utils.Utils;

import ch.qos.logback.classic.pattern.Util;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public void run(ApplicationArguments args) {
		
		roleRepository.save(new Role(1L,"ROLE_ADMIN"));
		roleRepository.save(new Role(2L,"ROLE_CONSUMER"));
		roleRepository.save(new Role(3L,"ROLE_SUPERADMIN"));
		
		configurationRepository.save(new Configuration("REFERRAL_BONUS","100"));
		configurationRepository.save(new Configuration("GST_RATE","10"));
		
		configurationRepository.save(new Configuration("IM_PRIVATE_KEY","test_c0fe7a31708c15d1d1fd548d88c"));
		configurationRepository.save(new Configuration("IM_AUTH_TOKEN","test_75914bf49a32ffaaee32bf441b8"));
		configurationRepository.save(new Configuration("IM_SALT","fb7d6875d700457198525b308bcd1d52"));
		configurationRepository.save(new Configuration("IM_CLIENT_SECRET","test_uICB6FvnzLkYR0lS256p7WIVUkxO9Ddm4uyzwXWuqVhSKbvdUVoOsLWkFHiW0w1VMlXVtjZ8WWXa3wSTHieGqGVxxbqhrTnkxrbWWiUPhT0rTHGnrcd200TAMnE"));
		configurationRepository.save(new Configuration("IM_CLIENT_ID","test_6I2TqQfGs3yXN2SH9yfgQJgL0sV4NvSNy1V"));
		
		userRepository.save(new User(1L,"ADMIN", "PANEL", "test@gmail.com", "9311982278", true, true, false, Long.valueOf(0), Long.valueOf(0), "admin", bCryptPasswordEncoder.encode("admin"), roleRepository.findById(3L).get()));
	}
}