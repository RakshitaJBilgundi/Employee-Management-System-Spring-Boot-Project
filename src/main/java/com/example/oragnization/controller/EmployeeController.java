package com.example.oragnization.controller;

import com.example.oragnization.service.EmployeeImp;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import com.example.oragnization.dto.EmployeeRequest;
import com.example.oragnization.dto.EmployeeResponse;

@Slf4j
@RestController
@SecurityRequirement(name="Bearer Authentication")
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeImp employeeService;

	// localhost:5090/employee
	
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployee(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "empId") String sortBy,
			@RequestParam(required = false, defaultValue = "ASC") String sortDir,
			@RequestParam(required = false) int empId,
			@RequestParam(required = false) int age,@RequestParam(required = false) String empName,
			@RequestParam(required = false) String empEmail, @RequestParam(required = false) int deptId,
			@RequestParam(required = false) String deptName, @RequestParam(required = false) String location,
			@RequestParam(required = false) String phoneNumber

	) {
		log.info("Admin login attempt");
		// Pageable page=PageRequest.of(pageNo, pageSize,
		// Sort.by("empName").ascending());

		// Sort.by(sortBy).descending();
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("ASC")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		List<EmployeeResponse> emp = employeeService.getAllEmp(PageRequest.of(pageNo - 1, pageSize, sort), empId,age,
				 empName, empEmail, deptId, deptName, location, phoneNumber);
		if (CollectionUtils.isEmpty(emp)) {
			
			return ResponseEntity.noContent().build();
		}

		System.out.println("employee fetched successfully");
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int id) {
		try {
			log.info("Fetching the employee by id");
			EmployeeResponse empResponse = employeeService.getById(id);
			return new ResponseEntity<>(empResponse, HttpStatus.OK);

		} catch (RuntimeException e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/")
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest empRequest) {

		EmployeeResponse emp = employeeService.saveEmployee(empRequest);
		if (emp == null) {
			return new ResponseEntity(emp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest request) {

		EmployeeResponse response = employeeService.updateEmployee(id, request);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

		log.info("deleting the employee");
		if (employeeService.existsById(id)) {

			employeeService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");

		}
	}
	/*
	 * @RequestMapping( value = "/search", method = RequestMethod.valueOf("QUERY"),
	 * consumes = "application/json" ) public ResponseEntity<List<EmployeeResponse>>
	 * queryEmployees(
	 * 
	 * @RequestBody EmployeeFilterRequest filter,
	 * 
	 * @RequestParam(defaultValue = "1") int pageNo,
	 * 
	 * @RequestParam(defaultValue = "5") int pageSize,
	 * 
	 * @RequestParam(defaultValue = "empId") String sortBy,
	 * 
	 * @RequestParam(defaultValue = "ASC") String sortDir) {
	 * 
	 * Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() :
	 * Sort.by(sortBy).descending();
	 * 
	 * Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	 * 
	 * List<EmployeeResponse> employees = employeeService.getAllEmp( pageable,
	 * filter.getEmpId(), filter.getEmpName(), filter.getEmpEmail(),
	 * filter.getDeptId(), filter.getDeptName(), filter.getLocation(),
	 * filter.getPhoneNumber() );
	 * 
	 * return ResponseEntity.ok(employees); }
	 */

}
