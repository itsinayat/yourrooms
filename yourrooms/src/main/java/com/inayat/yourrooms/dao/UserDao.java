package com.inayat.yourrooms.dao;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.entity.UserToken;
import com.inayat.yourrooms.repositories.UserRepository;
import com.inayat.yourrooms.repositories.UserTokenRepository;
import com.inayat.yourrooms.utils.Constants;

@Service
@Transactional
@EnableJpaRepositories(basePackages = { "com.inayat.yourrooms.repositories" })
public class UserDao {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
	UserTokenRepository userTokenRepository;

	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public boolean checkUserToken(String token, String username) {
		UserToken userToken = userTokenRepository.checkUserToken(token, username, Constants.UsetTokenStatus.ACTIVE);
		
		if (userToken != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public UserToken saveUserToken(UserToken userToken) {
		if (userToken.getTokenId() > 0) {
			userToken.setUdate(new Date());
		} else {
			userToken.setCdate(new Date());
		}
		
		return userTokenRepository.save(userToken);
	}
	
	public UserToken getUserTokenByTokenKey(String tokenKey) {
		return userTokenRepository.findUserTokenByTokenKey(tokenKey);
	}
	
	public void deactivateUserToken(User user){
		UserToken userToken = userTokenRepository.findUserTokenByUsername(user.getUsername(), Constants.UsetTokenStatus.ACTIVE);
		userToken.setStatus(Constants.UsetTokenStatus.DEACTIVE);
		userTokenRepository.save(userToken);
	}

}
