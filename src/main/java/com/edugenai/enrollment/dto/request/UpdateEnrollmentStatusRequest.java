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
public class UpdateEnrollmentStatusRequest {

    @NotNull(message = "Enrollment status is required")
    private EnrollmentStatus status;

    private String reason;

    private String changedBy;
}

