package com.edugenai.enrollment.service.impl;
import com.edugenai.enrollment.dto.request.SubscriptionRequest;
import com.edugenai.enrollment.dto.response.SubscriptionResponse;
import com.edugenai.enrollment.entity.Subscription;
import com.edugenai.enrollment.exception.ResourceNotFoundException;
import com.edugenai.enrollment.repository.SubscriptionRepository;
import com.edugenai.enrollment.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        if (subscriptionRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("Student already has an active subscription");
        }

        LocalDateTime now = LocalDateTime.now();
        Subscription subscription = Subscription.builder()
                .userId(request.getUserId())
                .planType("FREE_1_YEAR")
                .startDate(now)
                .endDate(now.plusYears(1))
                .status("ACTIVE")
                .build();
        
        Subscription saved = subscriptionRepository.save(subscription);
        return mapToResponse(saved);
    }

    @Override
    public SubscriptionResponse getSubscriptionByUserId(Long userId) {
        Subscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No subscription found for user: " + userId));
        return mapToResponse(subscription);
    }

    private SubscriptionResponse mapToResponse(Subscription subscription) {
        return SubscriptionResponse.builder()
                .subscriptionId(subscription.getSubscriptionId())
                .userId(subscription.getUserId())
                .planType(subscription.getPlanType())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .status(subscription.getStatus())
                .build();
    }
}
