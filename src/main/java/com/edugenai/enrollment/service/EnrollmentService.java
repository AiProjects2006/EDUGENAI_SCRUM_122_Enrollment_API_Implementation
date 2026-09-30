package com.edugenai.enrollment.service;

import com.edugenai.enrollment.dto.request.GradeEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createOrUpdateEnrollment(GradeEnrollmentRequest request);
}
