package com.example.oragnization.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeLoginRequest {

	@NotBlank(message="email should be not blank")
	@Email(message="valid email")
	private String empEmail;
	
	@NotBlank(message="password should not be blank")
	private String password;
}
