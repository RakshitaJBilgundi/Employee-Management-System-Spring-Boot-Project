package com.example.oragnization.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.oragnization.dto.EmployeeRequest;
import com.example.oragnization.dto.EmployeeResponse;
import com.example.oragnization.entity.Employee;

public interface EmployeeService {
//DTO->ENTITY
	
	public EmployeeResponse saveEmployee(EmployeeRequest EmployeeRequest);
	public EmployeeResponse updateEmployee(int id,EmployeeRequest EmployeeRequest);
	
	//ENTITY->DTO

	public List<EmployeeResponse> getAllEmp(Pageable pageable,int empId,int age,String empName,String empEmail,int deptId,String deptName,String location,String phoneNumber);

	public EmployeeResponse getById(int id);

	public boolean deleteById(int id);

	public boolean existsById(int id);

}
