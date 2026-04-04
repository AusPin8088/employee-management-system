package com.backend.employeemanagementsystem.repository;

import org.springframework.data.jpa.domain.Specification;

import com.backend.employeemanagementsystem.entity.Employee;


public final class EmployeeSpecifications {
    
    private EmployeeSpecifications() {
    }

    public static Specification<Employee> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + name.trim().toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), likePattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), likePattern)
            );
        };
    }

    public static Specification<Employee> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + email.trim().toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), likePattern);
        };
    }

    public static Specification<Employee> hasJobTitle(String jobTitle) {
        return (root, query, criteriaBuilder) -> {
            if (jobTitle == null || jobTitle.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + jobTitle.trim().toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("jobTitle")), likePattern);
        };
    }

    public static Specification<Employee> hasDepartmentId(Long departmentId) {
        return (root, query, criteriaBuilder) -> {
            if (departmentId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("department").get("id"), departmentId);
        };
    }
}
