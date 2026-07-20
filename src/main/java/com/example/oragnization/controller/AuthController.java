package com.example.oragnization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oragnization.dto.LoginRequest;
import com.example.oragnization.dto.LoginResponse;
import com.example.oragnization.service.AuthService;

import lombok.extern.slf4j.Slf4j;
	
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
 log.info("Logining using Admin and password");
	    System.out.println("Inside Login Controller");

	    return ResponseEntity.ok(authService.login(request));
	}
}
