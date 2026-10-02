package com.edugenai.enrollment.dto.request;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
@Data
public class CourseEnrollmentRequest {
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;
}
