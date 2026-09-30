package com.edugenai.enrollment.service;

import com.edugenai.enrollment.dto.request.GradeEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.Enrollment;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import com.edugenai.enrollment.enums.GradeLevel;
import com.edugenai.enrollment.repository.EnrollmentRepository;
import com.edugenai.enrollment.service.impl.EnrollmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    @Test
    public void testCreateEnrollment() {
        GradeEnrollmentRequest request = new GradeEnrollmentRequest();
        request.setStudentId(100L);
        request.setGradeLevel(GradeLevel.GRADE_4);

        Enrollment savedEnrollment = Enrollment.builder()
                .enrollmentId(1L)
                .studentId(100L)
                .gradeLevel(GradeLevel.GRADE_4)
                .status(EnrollmentStatus.ACTIVE)
                .enrollmentDate(LocalDateTime.now())
                .build();

        when(enrollmentRepository.findByStudentId(100L)).thenReturn(Optional.empty());
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(savedEnrollment);

        EnrollmentResponse response = enrollmentService.createOrUpdateEnrollment(request);

        assertNotNull(response);
        assertEquals(100L, response.getStudentId());
        assertEquals("PRIMARY", response.getCategory());
        verify(enrollmentRepository, times(1)).save(any(Enrollment.class));
    }
}
