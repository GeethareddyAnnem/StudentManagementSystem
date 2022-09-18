package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.controller.RecordNotFoundException;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetailsImpl;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
   private UserRepository ur;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = ur.findByUsername(username).get();
		if(user == null) {
			throw new RecordNotFoundException("User NOt Fount ."+username);
		}
		return new UserDetailsImpl(user);
      
    }
    
}
