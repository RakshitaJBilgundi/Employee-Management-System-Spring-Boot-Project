package com.example.oragnization.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.oragnization.entity.Department;
import com.example.oragnization.repository.DepartmentRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {


	private int empId;
	private int age;
	private String empName;
	private String empEmail;
	private String password;
	private DepartmentResponse deptResponse;
	private String location;
	private String phoneNumber;
	
}
