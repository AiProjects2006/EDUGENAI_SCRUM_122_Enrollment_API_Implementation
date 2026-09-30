package com.edugenai.enrollment.service;
import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
public interface EnrollmentService {
    EnrollmentResponse createEnrollment(CourseEnrollmentRequest request);
}
