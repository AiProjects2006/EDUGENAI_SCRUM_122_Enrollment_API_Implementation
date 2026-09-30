package com.edugenai.enrollment.dto.response;

import com.edugenai.enrollment.enums.EnrollmentStatus;
import com.edugenai.enrollment.enums.GradeLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private Long enrollmentId;
    private Long studentId;
    private GradeLevel gradeLevel;

    private EnrollmentStatus status;
    private LocalDateTime enrollmentDate;
    private LocalDateTime updatedAt;
}
