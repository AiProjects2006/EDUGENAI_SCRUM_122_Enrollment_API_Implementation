package com.edugenai.enrollment.service;
import com.edugenai.enrollment.dto.request.SubscriptionRequest;
import com.edugenai.enrollment.dto.response.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse createSubscription(SubscriptionRequest request);
    SubscriptionResponse getSubscriptionByUserId(Long userId);
}
