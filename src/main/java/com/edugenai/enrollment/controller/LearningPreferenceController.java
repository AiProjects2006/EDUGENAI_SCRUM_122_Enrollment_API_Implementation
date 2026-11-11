package com.edugenai.enrollment.controller;
import com.edugenai.enrollment.dto.request.LearningPreferenceRequest;
import com.edugenai.enrollment.dto.response.LearningPreferenceResponse;
import com.edugenai.enrollment.service.LearningPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/preferences")
@RequiredArgsConstructor
public class LearningPreferenceController {
    private final LearningPreferenceService preferenceService;

    @PostMapping
    public ResponseEntity<LearningPreferenceResponse> createPreference(@Valid @RequestBody LearningPreferenceRequest request) {
        return new ResponseEntity<>(preferenceService.createPreference(request), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<LearningPreferenceResponse> getPreferenceByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(preferenceService.getPreferenceByUserId(userId));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<LearningPreferenceResponse> updatePreference(@PathVariable Long userId, @Valid @RequestBody LearningPreferenceRequest request) {
        return ResponseEntity.ok(preferenceService.updatePreference(userId, request));
    }
}
