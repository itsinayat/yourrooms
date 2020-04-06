package com.inayat.yourrooms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.inayat.yourrooms.entity.Configuration;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.repositories.ConfigurationRepository;
import com.inayat.yourrooms.repositories.RoleRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ConfigurationRepository configurationRepository;

	public void run(ApplicationArguments args) {
		roleRepository.save(new Role(1L,"ROLE_ADMIN"));
		roleRepository.save(new Role(2L,"ROLE_CONSUMER"));
		roleRepository.save(new Role(3L,"ROLE_OWNER"));
		configurationRepository.save(new Configuration(1L,"referral_bonus","100"));
		
	}
}