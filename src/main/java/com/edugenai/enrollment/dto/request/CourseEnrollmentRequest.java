package com.edugenai.enrollment.dto.request;

import com.edugenai.enrollment.enums.EnrollmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEnrollmentRequest {
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

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

    private EnrollmentStatus status;
}
