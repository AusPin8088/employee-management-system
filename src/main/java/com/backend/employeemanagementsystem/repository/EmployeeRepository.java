package com.backend.employeemanagementsystem.repository;

import com.backend.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmailIgnoreCase(String email);
}
