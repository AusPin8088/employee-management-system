package com.backend.employeemanagementsystem.dto;

import java.math.BigDecimal;

public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private BigDecimal salary;
    private DepartmentSummaryResponse department;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long id, String firstName, String lastName, String email, String jobTitle,
            BigDecimal salary, DepartmentSummaryResponse department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public DepartmentSummaryResponse getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentSummaryResponse department) {
        this.department = department;
    }
}
