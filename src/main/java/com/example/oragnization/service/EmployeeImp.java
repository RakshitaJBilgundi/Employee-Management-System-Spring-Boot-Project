package com.example.oragnization.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.oragnization.Specfication.EmployeeSpecification;
import com.example.oragnization.dto.DepartmentResponse;
import com.example.oragnization.dto.EmployeeRequest;
import com.example.oragnization.dto.EmployeeResponse;
import com.example.oragnization.entity.Department;
import com.example.oragnization.entity.Employee;
import com.example.oragnization.repository.DepartmentRepository;
import com.example.oragnization.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeImp implements EmployeeService {
	@Autowired
	private DepartmentRepository deptrepo;

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
	    try {
	    	log.info("saved the employee details");
	    	System.out.println(employeeRequest);
	        System.out.println(employeeRequest.getDeptRequest());


	        Employee emp = mapper.map(employeeRequest, Employee.class);

	        Department dept = deptrepo.findById(employeeRequest.getDeptRequest().getDeptId())
	                .orElseThrow(() -> new RuntimeException("Department not found"));

	        emp.setDepartment(dept);

	        Employee saveEmp = employeeRepo.save(emp);
	        
	        return mapToResponse(saveEmp);

	    } catch (RuntimeException e) {
	    	
	        log.error("Failed to save employee", e);
	        
	        throw new RuntimeException("Unable to save employee");
	    }
	}

	@Override
	public EmployeeResponse updateEmployee(int id, EmployeeRequest employeeRequest) {

		try {
			log.info("updated the employee through id");

			Employee existingEmployee = employeeRepo.findById(id)
					.orElseThrow(() -> new RuntimeException("Employee not found"));

			mapper.map(employeeRequest, existingEmployee);

			Department dept = deptrepo.findById(employeeRequest.getDeptRequest().getDeptId())
					.orElseThrow(() -> new RuntimeException("Department not found"));

			existingEmployee.setDepartment(dept);

			Employee updateEmployee=employeeRepo.save(existingEmployee);

			return mapToResponse(updateEmployee);

		} catch (Exception e) {

			log.error("Failed to update employee", e);

			throw new RuntimeException("Unable to update employee");
		}
	}

	@Override
	public List<EmployeeResponse> getAllEmp(Pageable pageable, int empId,int age,String empName, String empEmail,int deptId,String deptName,String location, String phoneNumber) {

	    try {

	    	log.info("getting all info about the employee");
	       Specification spec=EmployeeSpecification.getSpecification(empId,age,empName,empEmail,deptId,deptName,location,phoneNumber);
	       List<Employee> emp=employeeRepo.findAll(spec,pageable).getContent();

	        List<EmployeeResponse> empList = emp.stream().map(this::mapToResponse).toList();

	        return empList;
	        
	    } catch (Exception e) {
	        log.error("Failed to fetch employees", e);
	        throw new RuntimeException("Unable to fetch employees");
	    }
	}


	@Override
	public EmployeeResponse getById(int id) {
		try {
			log.info("getting the employee by id");
			Employee emp = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("check your id or dept id is missing"+id));
		
			return mapToResponse(emp);
		} catch (RuntimeException e) {
			 log.error("Failed to fetch employee", e);
		        throw new RuntimeException("Unable to fetch employee");
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			Optional<Employee> emp = employeeRepo.findById(id);
			if (emp.isPresent()) {
				employeeRepo.deleteById(id);
				return true;
			}
			return false;
		} catch (RuntimeException r) {
			log.error("error to delete by id");
			return false;
		}
	}

	@Override
	public boolean existsById(int id) {
		try {
			return employeeRepo.existsById(id);

		} catch (RuntimeException r) {
			log.error("failed to exist by id");
			throw new RuntimeException("check the id exist");
		}

	}
	
	private EmployeeResponse mapToResponse(Employee employee) {
try {
	    EmployeeResponse response = mapper.map(employee, EmployeeResponse.class);

	    if (employee.getDepartment() != null) {

	        DepartmentResponse deptResponse =
	                mapper.map(employee.getDepartment(), DepartmentResponse.class);

	        response.setDeptResponse(deptResponse);
	    }

	    return response;
	}
catch(RuntimeException e) {
	log.error("failed to mapp the employee response");
	throw new RuntimeException("check the mapping");
}

}
}
