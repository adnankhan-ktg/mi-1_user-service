package com.intelliatech.spring_security;

import com.intelliatech.dao.UserDao;
import com.intelliatech.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
 @Autowired
 private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
	
             User user = new User();
             
             user = this.userDao.findByUsername(username);
             if(user == null)
             {
            	 throw new UsernameNotFoundException(username);
             }
             return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());

		 
		 
		 
		 
		 
		 
	}

	}
