package com.example.oragnization.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.oragnization.dto.LoginRequest;
import com.example.oragnization.dto.LoginResponse;
import com.example.oragnization.security.JwtService;
@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	public LoginResponse login(LoginRequest request) {

	    try {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                request.getUserName(),
	                request.getPassword()
	            )
	        );

	        System.out.println("Authentication Successful");

	        String token = jwtService.generateToken(request.getUserName());
	        System.out.println("generated token"+token);
	        return new LoginResponse(token);

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	
}
