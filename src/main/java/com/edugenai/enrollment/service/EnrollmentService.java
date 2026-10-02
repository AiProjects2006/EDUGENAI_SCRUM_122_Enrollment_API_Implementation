package com.edugenai.enrollment.service;
import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.EnrollmentHistory;
import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(CourseEnrollmentRequest request);
    EnrollmentResponse getEnrollmentById(Long enrollmentId);
    List<EnrollmentResponse> getEnrollmentsByUserId(Long userId);
    List<EnrollmentResponse> getAllEnrollments();
    List<EnrollmentHistory> getEnrollmentHistory(Long enrollmentId);
}
