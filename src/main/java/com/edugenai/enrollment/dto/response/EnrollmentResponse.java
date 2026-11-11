package com.edugenai.enrollment.dto.response;

import com.edugenai.enrollment.enums.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {
    private Long enrollmentId;
    private Long userId;
    private Long courseId;
    private EnrollmentStatus status;

    private String studentName;
    private String studentEmail;
    private String studentGrade;
    private String studentAvatar;
    private String requestMessage;

    private String courseTitle;
    private String subjectName;
    private String gradeLevel;

    private Long creatorId;
    private String creatorName;
    private String creatorEmail;

    private String rejectionReason;
    private LocalDateTime respondedAt;

    private LocalDateTime enrollmentDate;
    private LocalDateTime lastAccessed;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
}
