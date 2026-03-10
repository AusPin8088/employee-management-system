package com.backend.employeemanagementsystem;

import com.backend.employeemanagementsystem.entity.Department;
import com.backend.employeemanagementsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DepartmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        departmentRepository.deleteAll();
    }

    @Test
    void shouldCreateDepartment() throws Exception {
        String requestBody = """
                {
                  "name": "Engineering"
                }
                """;

        mockMvc.perform(post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Engineering"));
    }

    @Test
    void shouldListDepartments() throws Exception {
        departmentRepository.save(new Department("Engineering"));
        departmentRepository.save(new Department("Finance"));

        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
