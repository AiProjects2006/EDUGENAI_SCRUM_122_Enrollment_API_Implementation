package com.edugenai.enrollment.service.impl;
import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.Enrollment;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import com.edugenai.enrollment.exception.ResourceNotFoundException;
import com.edugenai.enrollment.repository.EnrollmentRepository;
import com.edugenai.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
        return mapToResponse(savedEnrollment);
    }

    @Override
    public EnrollmentResponse getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
        return mapToResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EnrollmentResponse mapToResponse(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .userId(enrollment.getUserId())
                .courseId(enrollment.getCourseId())
                .status(enrollment.getStatus())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
    }
}
