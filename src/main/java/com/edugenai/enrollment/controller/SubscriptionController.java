package com.edugenai.enrollment.controller;
import com.edugenai.enrollment.dto.request.SubscriptionRequest;
import com.edugenai.enrollment.dto.response.SubscriptionResponse;
import com.edugenai.enrollment.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody SubscriptionRequest request) {
        return new ResponseEntity<>(subscriptionService.createSubscription(request), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<SubscriptionResponse> getSubscriptionByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByUserId(userId));
    }
}
