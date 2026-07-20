package com.example.oragnization.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.oragnization.dto.DepartmentRequest;
import com.example.oragnization.dto.DepartmentResponse;
import com.example.oragnization.entity.Department;
import com.example.oragnization.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository deptRepo;
    private final ModelMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository deptRepo,
                                 ModelMapper mapper) {
        this.deptRepo = deptRepo;
        this.mapper = mapper;
    }

    @Override
    public List<DepartmentResponse> getAllDepartment() {

        return deptRepo.findAll()
                .stream()
                .map(dept -> mapper.map(dept, DepartmentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getById(int id) {

        Department dept = deptRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department Not Found"));

        return mapper.map(dept, DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse saveDepartment(DepartmentRequest deptRequest) {

        Department dept = mapper.map(deptRequest, Department.class);

        Department savedDept = deptRepo.save(dept);

        return mapper.map(savedDept, DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse updateDepartment(int id,
                                               DepartmentRequest deptRequest) {

        Department dept = deptRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department Not Found"));

        dept.setdeptName(deptRequest.getDeptName());

        Department updatedDept = deptRepo.save(dept);

        return mapper.map(updatedDept, DepartmentResponse.class);
    }

    @Override
    public void deleteById(int id) {

        deptRepo.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {

        return deptRepo.existsById(id);
    }

}