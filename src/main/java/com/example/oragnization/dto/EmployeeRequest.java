package com.example.oragnization.dto;

import com.example.oragnization.validation.Phonenumber;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
	
	
	@Positive(message="It should be between 101 to anyth")
	private int empId;
	@NotBlank(message="name should not be blank")
	private String empName;
	@NotBlank(message="location should not be blank")
	private String location;

	@Min(18)
	@Max(60)
	private int age;
	
	@Phonenumber
	private String phoneNumber;

	@NotBlank(message = "dept should not be blank")
	@Email(message = "it should be in email format")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "email is inappropriate")
	private String empEmail;
	
	@NotNull
	private String password;
   
	@Valid
	private DepartmentRequest deptRequest;

	
}
