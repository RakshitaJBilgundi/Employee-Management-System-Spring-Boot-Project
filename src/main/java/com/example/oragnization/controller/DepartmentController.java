package com.example.oragnization.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.oragnization.dto.DepartmentRequest;
import com.example.oragnization.dto.DepartmentResponse;

import com.example.oragnization.service.DepartmentServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private  DepartmentServiceImpl deptService;
	public DepartmentController(DepartmentServiceImpl deptService) {
	
        this.deptService = deptService;
    }



//local:5091/department
	@GetMapping("/")
	public List<DepartmentResponse> getAllDepartment() {

		return deptService.getAllDepartment();

	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable int id) {
		return ResponseEntity.ok(deptService.getById(id));
	}

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody @Valid DepartmentRequest deptRequest) {
		DepartmentResponse deptresponse=deptService.saveDepartment(deptRequest);
		if (deptresponse == null) {
			return new ResponseEntity<>(deptresponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(deptresponse, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable int id,@RequestBody @Valid DepartmentRequest deptRequest) {
		DepartmentResponse dept = deptService.updateDepartment(id,deptRequest);
		
      return ResponseEntity.ok(dept);
	}

	@DeleteMapping("/{id}")
	public String deleteDepartment(@PathVariable int id) {
		if (deptService.existsById(id)) {
			deptService.deleteById(id);
			return "Department and its employee deleted";
		}
		return " Department Not found";
	}

}