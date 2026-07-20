package com.example.oragnization.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oragnization.entity.Department;
import com.example.oragnization.entity.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
