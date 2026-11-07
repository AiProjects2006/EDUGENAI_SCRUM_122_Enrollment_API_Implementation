package com.edugenai.enrollment.service.impl;

import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.request.UpdateEnrollmentStatusRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.Enrollment;
import com.edugenai.enrollment.entity.EnrollmentHistory;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import com.edugenai.enrollment.exception.ResourceNotFoundException;
import com.edugenai.enrollment.repository.EnrollmentHistoryRepository;
import com.edugenai.enrollment.repository.EnrollmentRepository;
import com.edugenai.enrollment.repository.SubscriptionRepository;
import com.edugenai.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentHistoryRepository enrollmentHistoryRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public EnrollmentResponse createEnrollment(CourseEnrollmentRequest request) {
        EnrollmentStatus targetStatus = request.getStatus() != null
                ? request.getStatus()
                : EnrollmentStatus.PENDING;

        Optional<Enrollment> existingOpt = enrollmentRepository.findByUserIdAndCourseId(
                request.getUserId(),
                request.getCourseId()
        );

        if (existingOpt.isPresent()) {
            Enrollment existing = existingOpt.get();
            applyRequestMetadata(existing, request);

            if (existing.getStatus() == EnrollmentStatus.REJECTED
                    || existing.getStatus() == EnrollmentStatus.DROPPED) {
                existing.setStatus(targetStatus);
                existing.setRejectionReason(null);
                existing.setRespondedAt(null);
                if (request.getRequestMessage() != null) {
                    existing.setRequestMessage(request.getRequestMessage());
                }
                Enrollment reopened = enrollmentRepository.save(existing);

                enrollmentHistoryRepository.save(EnrollmentHistory.builder()
                        .enrollmentId(reopened.getEnrollmentId())
                        .status(reopened.getStatus().name())
                        .changedBy(request.getStudentName() != null ? request.getStudentName() : "STUDENT")
                        .build());
                return mapToResponse(reopened);
            }

            if (existing.getStatus() == EnrollmentStatus.PENDING
                    && request.getRequestMessage() != null
                    && !request.getRequestMessage().isBlank()) {
                existing.setRequestMessage(request.getRequestMessage());
            }
            Enrollment updatedExisting = enrollmentRepository.save(existing);
            return mapToResponse(updatedExisting);
        }

        Enrollment enrollment = Enrollment.builder()
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .status(targetStatus)
                .studentName(request.getStudentName())
                .studentEmail(request.getStudentEmail())
                .studentGrade(request.getStudentGrade())
                .studentAvatar(request.getStudentAvatar())
                .requestMessage(request.getRequestMessage())
                .courseTitle(request.getCourseTitle())
                .subjectName(request.getSubjectName())
                .gradeLevel(request.getGradeLevel())
                .creatorId(request.getCreatorId())
                .creatorName(request.getCreatorName())
                .creatorEmail(request.getCreatorEmail())
                .build();

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        EnrollmentHistory history = EnrollmentHistory.builder()
                .enrollmentId(savedEnrollment.getEnrollmentId())
                .status(savedEnrollment.getStatus().name())
                .changedBy(request.getStudentName() != null ? request.getStudentName() : "STUDENT")
                .build();
        enrollmentHistoryRepository.save(history);

        return mapToResponse(savedEnrollment);
    }

    private void applyRequestMetadata(Enrollment enrollment, CourseEnrollmentRequest request) {
        if (request.getStudentName() != null && !request.getStudentName().isBlank()) {
            enrollment.setStudentName(request.getStudentName());
        }
        if (request.getStudentEmail() != null && !request.getStudentEmail().isBlank()) {
            enrollment.setStudentEmail(request.getStudentEmail());
        }
        if (request.getStudentGrade() != null && !request.getStudentGrade().isBlank()) {
            enrollment.setStudentGrade(request.getStudentGrade());
        }
        if (request.getStudentAvatar() != null && !request.getStudentAvatar().isBlank()) {
            enrollment.setStudentAvatar(request.getStudentAvatar());
        }
        if (request.getCourseTitle() != null && !request.getCourseTitle().isBlank()) {
            enrollment.setCourseTitle(request.getCourseTitle());
        }
        if (request.getSubjectName() != null && !request.getSubjectName().isBlank()) {
            enrollment.setSubjectName(request.getSubjectName());
        }
        if (request.getGradeLevel() != null && !request.getGradeLevel().isBlank()) {
            enrollment.setGradeLevel(request.getGradeLevel());
        }
        if (request.getCreatorId() != null) {
            enrollment.setCreatorId(request.getCreatorId());
        }
        if (request.getCreatorName() != null && !request.getCreatorName().isBlank()) {
            enrollment.setCreatorName(request.getCreatorName());
        }
        if (request.getCreatorEmail() != null && !request.getCreatorEmail().isBlank()) {
            enrollment.setCreatorEmail(request.getCreatorEmail());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentResponse getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
        return mapToResponse(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getEnrollmentsByCreator(Long creatorId, String creatorEmail, String creatorName) {
        List<Enrollment> all = enrollmentRepository.findAllByOrderByCreateDateDesc();
        return all.stream()
                .filter(e -> {
                    boolean idMatch = creatorId != null && creatorId.equals(e.getCreatorId());
                    boolean emailMatch = creatorEmail != null && !creatorEmail.isBlank()
                            && e.getCreatorEmail() != null
                            && creatorEmail.trim().equalsIgnoreCase(e.getCreatorEmail().trim());
                    boolean nameMatch = creatorName != null && !creatorName.isBlank()
                            && e.getCreatorName() != null
                            && creatorName.trim().equalsIgnoreCase(e.getCreatorName().trim());
                    return idMatch || emailMatch || nameMatch;
                })
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseIdOrderByCreateDateDesc(courseId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAllByOrderByCreateDateDesc().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponse updateEnrollmentStatus(Long enrollmentId, UpdateEnrollmentStatusRequest request) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));

        enrollment.setStatus(request.getStatus());
        enrollment.setRespondedAt(LocalDateTime.now());

        if (request.getStatus() == EnrollmentStatus.REJECTED) {
            enrollment.setRejectionReason(request.getReason());
        } else if (request.getStatus() == EnrollmentStatus.ACTIVE) {
            enrollment.setRejectionReason(null);
            enrollment.setLastAccessed(LocalDateTime.now());
        }

        Enrollment saved = enrollmentRepository.save(enrollment);

        EnrollmentHistory history = EnrollmentHistory.builder()
                .enrollmentId(saved.getEnrollmentId())
                .status(saved.getStatus().name())
                .changedBy(request.getChangedBy() != null && !request.getChangedBy().isBlank()
                        ? request.getChangedBy()
                        : "CONTENT_CREATOR")
                .build();
        enrollmentHistoryRepository.save(history);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentHistory> getEnrollmentHistory(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId);
        }
        return enrollmentHistoryRepository.findByEnrollmentIdOrderByChangedDateDesc(enrollmentId);
    }

    private EnrollmentResponse mapToResponse(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .userId(enrollment.getUserId())
                .courseId(enrollment.getCourseId())
                .status(enrollment.getStatus())
                .studentName(enrollment.getStudentName())
                .studentEmail(enrollment.getStudentEmail())
                .studentGrade(enrollment.getStudentGrade())
                .studentAvatar(enrollment.getStudentAvatar())
                .requestMessage(enrollment.getRequestMessage())
                .courseTitle(enrollment.getCourseTitle())
                .subjectName(enrollment.getSubjectName())
                .gradeLevel(enrollment.getGradeLevel())
                .creatorId(enrollment.getCreatorId())
                .creatorName(enrollment.getCreatorName())
                .creatorEmail(enrollment.getCreatorEmail())
                .rejectionReason(enrollment.getRejectionReason())
                .respondedAt(enrollment.getRespondedAt())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .lastAccessed(enrollment.getLastAccessed())
                .createDate(enrollment.getCreateDate())
                .lastUpdate(enrollment.getLastUpdate())
                .build();
    }
}
