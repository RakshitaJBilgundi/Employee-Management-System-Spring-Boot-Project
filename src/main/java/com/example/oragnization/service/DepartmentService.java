package com.example.oragnization.service;

import java.util.List;

import com.example.oragnization.dto.DepartmentRequest;
import com.example.oragnization.dto.DepartmentResponse;
import com.example.oragnization.entity.Department;
import com.example.oragnization.entity.Employee;

public interface DepartmentService {
	public List<DepartmentResponse> getAllDepartment();

	public DepartmentResponse getById(int id);

	public DepartmentResponse saveDepartment(DepartmentRequest deptRequest);
	DepartmentResponse updateDepartment(int id, DepartmentRequest deptRequest);

	public void deleteById(int id);

	public boolean existsById(int id);

}
