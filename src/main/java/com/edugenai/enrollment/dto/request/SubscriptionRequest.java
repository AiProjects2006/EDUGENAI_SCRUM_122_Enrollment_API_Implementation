package com.edugenai.enrollment.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
}
