package com.backend.employeemanagementsystem.service;

import com.backend.employeemanagementsystem.dto.DepartmentSummaryResponse;
import com.backend.employeemanagementsystem.dto.EmployeeRequest;
import com.backend.employeemanagementsystem.dto.EmployeeResponse;
import com.backend.employeemanagementsystem.dto.PagedResponse;
import com.backend.employeemanagementsystem.entity.Department;
import com.backend.employeemanagementsystem.entity.Employee;
import com.backend.employeemanagementsystem.exception.DuplicateResourceException;
import com.backend.employeemanagementsystem.exception.ResourceNotFoundException;
import com.backend.employeemanagementsystem.repository.DepartmentRepository;
import com.backend.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        String email = employeeRequest.getEmail().trim();

        if (employeeRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Employee email already exists");
        }

        Department department = getDepartmentById(employeeRequest.getDepartmentId());
        Employee employee = new Employee(
                employeeRequest.getFirstName().trim(),
                employeeRequest.getLastName().trim(),
                email,
                employeeRequest.getJobTitle().trim(),
                employeeRequest.getSalary(),
                department);

        Employee savedEmployee = employeeRepository.save(employee);
        return mapEmployee(savedEmployee);
    }

    @Transactional(readOnly = true)
    public PagedResponse<EmployeeResponse> getEmployees(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(resolveSortDirection(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        return new PagedResponse<>(
                employeePage.getContent()
                .stream()
                .map(this::mapEmployee)
                .toList(),
                employeePage.getNumber(),
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages(),
                employeePage.isFirst(),
                employeePage.isLast());
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = getEmployeeEntityById(id);
        return mapEmployee(employee);
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee existingEmployee = getEmployeeEntityById(id);
        Department department = getDepartmentById(employeeRequest.getDepartmentId());
        String email = employeeRequest.getEmail().trim();

        if (!existingEmployee.getEmail().equalsIgnoreCase(email)
                && employeeRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateResourceException("Employee email already exists");
        }

        existingEmployee.setFirstName(employeeRequest.getFirstName().trim());
        existingEmployee.setLastName(employeeRequest.getLastName().trim());
        existingEmployee.setEmail(email);
        existingEmployee.setJobTitle(employeeRequest.getJobTitle().trim());
        existingEmployee.setSalary(employeeRequest.getSalary());
        existingEmployee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return mapEmployee(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeEntityById(id);
        employeeRepository.delete(employee);
    }

    private Employee getEmployeeEntityById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    private Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + departmentId));
    }

    private Sort.Direction resolveSortDirection(String sortDirection) {
        return "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    private EmployeeResponse mapEmployee(Employee employee) {
        Department department = employee.getDepartment();
        DepartmentSummaryResponse departmentSummary = new DepartmentSummaryResponse(
                department.getId(),
                department.getName());

        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getJobTitle(),
                employee.getSalary(),
                departmentSummary);
    }
}
