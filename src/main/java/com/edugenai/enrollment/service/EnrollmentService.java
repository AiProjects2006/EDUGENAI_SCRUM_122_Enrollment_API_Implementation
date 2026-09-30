package com.edugenai.enrollment.service;
import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(CourseEnrollmentRequest request);
    EnrollmentResponse getEnrollmentById(Long enrollmentId);
    List<EnrollmentResponse> getEnrollmentsByUserId(Long userId);
}
