package com.edugenai.enrollment.service.impl;
import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.Enrollment;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import com.edugenai.enrollment.repository.EnrollmentRepository;
import com.edugenai.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    @Override
    public EnrollmentResponse createEnrollment(CourseEnrollmentRequest request) {
        Enrollment enrollment = Enrollment.builder()
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .status(EnrollmentStatus.ACTIVE)
                .build();
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return EnrollmentResponse.builder()
                .enrollmentId(savedEnrollment.getEnrollmentId())
                .userId(savedEnrollment.getUserId())
                .courseId(savedEnrollment.getCourseId())
                .status(savedEnrollment.getStatus())
                .enrollmentDate(savedEnrollment.getEnrollmentDate())
                .build();
    }
}
