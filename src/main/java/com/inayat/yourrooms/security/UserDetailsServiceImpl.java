package com.inayat.yourrooms.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inayat.yourrooms.dao.UserDao;
import com.inayat.yourrooms.entity.Role;
import com.inayat.yourrooms.entity.User;
import com.inayat.yourrooms.repositories.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserRepository userRepository;

    private User userCache = null;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 User user =null;
    	if (userCache !=null){
	    user = userCache;
    	}else {
    		user=userRepository.findByUsername(username);
    	}
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
	        UserDetails ud= buildUserForAuthentication(user, authorities);
	        return ud;
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(Role list) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    roles.add(new SimpleGrantedAuthority(list.getName()));

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}