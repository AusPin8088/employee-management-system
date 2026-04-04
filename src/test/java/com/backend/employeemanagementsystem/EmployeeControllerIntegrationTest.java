package com.backend.employeemanagementsystem;

import com.backend.employeemanagementsystem.entity.Department;
import com.backend.employeemanagementsystem.entity.Employee;
import com.backend.employeemanagementsystem.repository.DepartmentRepository;
import com.backend.employeemanagementsystem.repository.EmployeeRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Department engineeringDepartment;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();
        engineeringDepartment = departmentRepository.save(new Department("Engineering"));
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        String requestBody = employeePayload("Alicia", "Tan", "alicia.tan@example.com", "Backend Developer", "5200.00");

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.firstName").value("Alicia"))
                .andExpect(jsonPath("$.department.name").value("Engineering"));
    }

    @Test
    void shouldListEmployees() throws Exception {
        employeeRepository.save(new Employee(
                "Alicia",
                "Tan",
                "alicia.tan@example.com",
                "Backend Developer",
                new BigDecimal("5200.00"),
                engineeringDepartment));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].email").value("alicia.tan@example.com"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(true));
    }

    @Test
    void shouldSupportPaginationAndSortingWhenListingEmployees() throws Exception {
        employeeRepository.save(new Employee(
                "Carla",
                "Ng",
                "carla.ng@example.com",
                "Support Engineer",
                new BigDecimal("3900.00"),
                engineeringDepartment));
        employeeRepository.save(new Employee(
                "Ben",
                "Lee",
                "ben.lee@example.com",
                "QA Engineer",
                new BigDecimal("4200.00"),
                engineeringDepartment));
        employeeRepository.save(new Employee(
                "Alicia",
                "Tan",
                "alicia.tan@example.com",
                "Backend Developer",
                new BigDecimal("5200.00"),
                engineeringDepartment));

        mockMvc.perform(get("/api/employees")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sortBy", "firstName")
                        .param("sortDirection", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].firstName").value("Carla"))
                .andExpect(jsonPath("$.content[1].firstName").value("Ben"))
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.totalPages").value(2))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(false));
    }

    @Test
    void shouldReturnEmployeeById() throws Exception {
        Employee savedEmployee = employeeRepository.save(new Employee(
                "Ben",
                "Lee",
                "ben.lee@example.com",
                "QA Engineer",
                new BigDecimal("4200.00"),
                engineeringDepartment));

        mockMvc.perform(get("/api/employees/{id}", savedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedEmployee.getId()))
                .andExpect(jsonPath("$.firstName").value("Ben"));
    }

    @Test
    void shouldReturnNotFoundForMissingEmployee() throws Exception {
        mockMvc.perform(get("/api/employees/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Employee not found with id 999"));
    }

    @Test
    void shouldUpdateEmployee() throws Exception {
        Employee savedEmployee = employeeRepository.save(new Employee(
                "Carla",
                "Ng",
                "carla.ng@example.com",
                "Support Engineer",
                new BigDecimal("3900.00"),
                engineeringDepartment));

        String requestBody = employeePayload("Carla", "Ng", "carla.ng@example.com", "Senior Support Engineer", "4500.00");

        mockMvc.perform(put("/api/employees/{id}", savedEmployee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobTitle").value("Senior Support Engineer"))
                .andExpect(jsonPath("$.salary").value(4500.00));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        Employee savedEmployee = employeeRepository.save(new Employee(
                "Daniel",
                "Lim",
                "daniel.lim@example.com",
                "Analyst",
                new BigDecimal("4100.00"),
                engineeringDepartment));

        mockMvc.perform(delete("/api/employees/{id}", savedEmployee.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/employees/{id}", savedEmployee.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRejectBlankFields() throws Exception {
        String requestBody = """
                {
                  "firstName": "",
                  "lastName": "",
                  "email": "not-an-email",
                  "jobTitle": "",
                  "salary": -1,
                  "departmentId": null
                }
                """;

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.validationErrors.length()").value(6));
    }

    private String employeePayload(String firstName, String lastName, String email, String jobTitle, String salary) {
        return """
                {
                  "firstName": "%s",
                  "lastName": "%s",
                  "email": "%s",
                  "jobTitle": "%s",
                  "salary": %s,
                  "departmentId": %d
                }
                """.formatted(
                firstName,
                lastName,
                email,
                jobTitle,
                salary,
                engineeringDepartment.getId());
    }
}
