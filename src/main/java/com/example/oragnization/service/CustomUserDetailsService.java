package com.example.oragnization.service;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.oragnization.entity.Admin;

import com.example.oragnization.repository.AdminRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final AdminRepository adminRepository;
	

	public CustomUserDetailsService(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    Admin admin = adminRepository.findByUserName(username);

	    System.out.println("Username from request = " + username);

	    if (admin == null) {
	        throw new UsernameNotFoundException("User not found");
	    }

	    System.out.println("DB Username = " + admin.getUserName());
	    System.out.println("DB Password = " + admin.getPassword());

	    return new User(
	            admin.getUserName(),
	            admin.getPassword(),
	            List.of(new SimpleGrantedAuthority("ROLE_"+admin.getRole().name()))
	    );
	}
	
	
}