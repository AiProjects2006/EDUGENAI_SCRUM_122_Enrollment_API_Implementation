package com.edugenai.enrollment.dto.response;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class SubscriptionResponse {
    private Long subscriptionId;
    private Long userId;
    private String planType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
}
