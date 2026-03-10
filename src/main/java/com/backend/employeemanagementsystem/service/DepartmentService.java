package com.backend.employeemanagementsystem.service;

import com.backend.employeemanagementsystem.dto.DepartmentRequest;
import com.backend.employeemanagementsystem.dto.DepartmentResponse;
import com.backend.employeemanagementsystem.entity.Department;
import com.backend.employeemanagementsystem.exception.DuplicateResourceException;
import com.backend.employeemanagementsystem.repository.DepartmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        String departmentName = departmentRequest.getName().trim();

        if (departmentRepository.existsByNameIgnoreCase(departmentName)) {
            throw new DuplicateResourceException("Department name already exists");
        }

        Department department = new Department(departmentName);
        Department savedDepartment = departmentRepository.save(department);
        return mapDepartment(savedDepartment);
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapDepartment)
                .toList();
    }

    private DepartmentResponse mapDepartment(Department department) {
        return new DepartmentResponse(department.getId(), department.getName());
    }
}
