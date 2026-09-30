package com.edugenai.enrollment.controller;

import com.edugenai.enrollment.dto.request.GradeEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import com.edugenai.enrollment.enums.GradeLevel;
import com.edugenai.enrollment.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentService enrollmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateOrUpdateEnrollment() throws Exception {
        GradeEnrollmentRequest request = new GradeEnrollmentRequest();
        request.setStudentId(100L);
        request.setGradeLevel(GradeLevel.GRADE_4);

        EnrollmentResponse response = EnrollmentResponse.builder()
                .studentId(100L)
                .gradeLevel(GradeLevel.GRADE_4)
                .category("PRIMARY")
                .status(EnrollmentStatus.ACTIVE)
                .build();

        Mockito.when(enrollmentService.createOrUpdateEnrollment(Mockito.any(GradeEnrollmentRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/enrollments/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentId").value(100))
                .andExpect(jsonPath("$.category").value("PRIMARY"));
    }
}
