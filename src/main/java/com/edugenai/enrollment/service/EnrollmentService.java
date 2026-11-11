package com.edugenai.enrollment.service;

import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.request.UpdateEnrollmentStatusRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.EnrollmentHistory;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(CourseEnrollmentRequest request);
    EnrollmentResponse getEnrollmentById(Long enrollmentId);
    List<EnrollmentResponse> getEnrollmentsByUserId(Long userId);
    List<EnrollmentResponse> getEnrollmentsByCreator(Long creatorId, String creatorEmail, String creatorName);
    List<EnrollmentResponse> getEnrollmentsByCourse(Long courseId);
    List<EnrollmentResponse> getAllEnrollments();
    EnrollmentResponse updateEnrollmentStatus(Long enrollmentId, UpdateEnrollmentStatusRequest request);
    List<EnrollmentHistory> getEnrollmentHistory(Long enrollmentId);
}
