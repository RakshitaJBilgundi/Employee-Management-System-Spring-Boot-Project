package com.example.oragnization.entity;

import com.example.oragnization.validation.Phonenumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class Employee {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	@Column(name = "emp_age", nullable = false)
	private int age;
	@Column(name = "emp_name")
	private String empName;
	@Column(name = "location")
	private String location;
	@Column(name = "empEmail")
	private String empEmail;
	@Phonenumber(message="Phone number is not in the required format")
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(nullable=false)
	private String password;
	

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", age=" + age + ", empName=" + empName + ", location=" + location + "]";
	}

	@ManyToOne
	@JoinColumn(name = "deptId")
	private Department department;

	public void setDepartment(Department dept) {
		this.department = dept;
		
	}
	public Department getDepartment() {
	    return department;
	}

}
