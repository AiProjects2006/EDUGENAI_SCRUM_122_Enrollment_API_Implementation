package com.edugenai.enrollment.dto.request;
import lombok.Data;
@Data
public class CourseEnrollmentRequest {
    private Long userId;
    private Long courseId;
}
