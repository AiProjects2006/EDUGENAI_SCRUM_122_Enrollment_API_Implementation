package com.edugenai.enrollment.service.impl;

import com.edugenai.enrollment.dto.request.GradeEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.Enrollment;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import com.edugenai.enrollment.exception.ResourceNotFoundException;
import com.edugenai.enrollment.repository.EnrollmentRepository;
import com.edugenai.enrollment.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public EnrollmentResponse createOrUpdateEnrollment(GradeEnrollmentRequest request) {
        Optional<Enrollment> existingEnrollmentOptional = enrollmentRepository.findByStudentId(request.getStudentId());

        Enrollment enrollment;
        if (existingEnrollmentOptional.isPresent()) {
            enrollment = existingEnrollmentOptional.get();
            enrollment.setGradeLevel(request.getGradeLevel());
            enrollment.setStatus(EnrollmentStatus.ACTIVE);
        } else {
            enrollment = Enrollment.builder()
                    .studentId(request.getStudentId())
                    .gradeLevel(request.getGradeLevel())
                    .status(EnrollmentStatus.ACTIVE)
                    .build();
        }

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return mapToResponse(savedEnrollment);
    }

    @Override
    public EnrollmentResponse getStudentEnrollment(Long studentId) {
        Enrollment enrollment = enrollmentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for student ID: " + studentId));
        return mapToResponse(enrollment);
    }

    private EnrollmentResponse mapToResponse(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .studentId(enrollment.getStudentId())
                .gradeLevel(enrollment.getGradeLevel())

                .status(enrollment.getStatus())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .updatedAt(enrollment.getUpdatedAt())
                .build();
    }
}
