package com.javaspringbooth2.projectcrudapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javaspringbooth2.projectcrudapi.dao.UserRepository;
import com.javaspringbooth2.projectcrudapi.entity.User;
import com.javaspringbooth2.projectcrudapi.model.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("In securty servive................");
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(()->new UsernameNotFoundException("Not Found: " + userName));
		return user.map(MyUserDetails::new).get();
	}
}
