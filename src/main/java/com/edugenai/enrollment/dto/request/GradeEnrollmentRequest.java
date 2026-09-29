package com.edugenai.enrollment.dto.request;

import com.edugenai.enrollment.enums.GradeLevel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeEnrollmentRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Grade Level is required")
    private GradeLevel gradeLevel;
}
