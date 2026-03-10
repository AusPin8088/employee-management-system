package com.backend.employeemanagementsystem.controller;

import com.backend.employeemanagementsystem.dto.DepartmentRequest;
import com.backend.employeemanagementsystem.dto.DepartmentResponse;
import com.backend.employeemanagementsystem.service.DepartmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @Valid @RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse createdDepartment = departmentService.createDepartment(departmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
    }

    @GetMapping
    public List<DepartmentResponse> getDepartments() {
        return departmentService.getAllDepartments();
    }
}
