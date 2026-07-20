package com.example.oragnization.Specfication;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import com.example.oragnization.entity.Employee;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeSpecification {

	public static Specification<Employee> getSpecification(int empId,int age,String empName, String empEmail,int deptId,String deptName,String location, String phoneNumber){
		
		return new Specification<Employee>() {
			
			public Predicate toPredicate(Root<Employee>root ,CriteriaQuery<?> query,CriteriaBuilder criteraBuilder) {
				List<Predicate> list =new ArrayList<>();
				if( empId>0) {
					list.add(criteraBuilder.equal(root.get("empId"),empId));
				}
				if( age>0) {
					list.add(criteraBuilder.equal(root.get("age"),age));
				}
				
				if(empName!=null|| !empName.isBlank()) {
					
					list.add(criteraBuilder.equal(root.get("empName"),empName));
				}
           if(empEmail!=null|| !empEmail.isBlank()) {
					list.add(criteraBuilder.equal(root.get("empEmail"),empEmail));
				}
           if(deptId>0) {
				list.add(criteraBuilder.equal(root.get("department").get("deptId"),deptId));
			}
           if(deptName!=null|| !deptName.isBlank()) {
				list.add(criteraBuilder.equal(root.get("department").get("deptName"),deptName));
			}
           if(location!=null|| !location.isBlank()) {
				list.add(criteraBuilder.equal(root.get("location"),location));
			}
           if(phoneNumber!=null|| !phoneNumber.isBlank()) {
				list.add(criteraBuilder.equal(root.get("phoneNumber"),phoneNumber));
				System.out.println("phoneNumber"+phoneNumber);
			}
				
				return criteraBuilder.or(list.toArray(new Predicate[0]));
			}
		};

			
	}
}
