package com.inayat.yourrooms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	

	@Transactional
	public List<User> getAllUsers() {
		List<User> users=(List<User>) userRepository.findAll();
		return users;
	}

}
