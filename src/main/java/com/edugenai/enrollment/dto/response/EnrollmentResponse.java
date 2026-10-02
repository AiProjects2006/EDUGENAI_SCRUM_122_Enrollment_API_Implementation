package com.edugenai.enrollment.dto.response;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class EnrollmentResponse {
    private Long enrollmentId;
    private Long userId;
    private Long courseId;
    private EnrollmentStatus status;
    private LocalDateTime enrollmentDate;
    private LocalDateTime lastAccessed;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
}
