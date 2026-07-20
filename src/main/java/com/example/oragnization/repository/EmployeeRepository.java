package com.example.oragnization.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.oragnization.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee> {
	
}

