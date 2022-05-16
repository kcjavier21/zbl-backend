package com.code.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.code.model.CustomUserDetails;
import com.code.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new CustomUserDetails(user);
	}
}
