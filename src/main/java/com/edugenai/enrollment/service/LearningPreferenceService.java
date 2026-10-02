package com.edugenai.enrollment.service;
import com.edugenai.enrollment.dto.request.LearningPreferenceRequest;
import com.edugenai.enrollment.dto.response.LearningPreferenceResponse;

public interface LearningPreferenceService {
    LearningPreferenceResponse createPreference(LearningPreferenceRequest request);
    LearningPreferenceResponse getPreferenceByUserId(Long userId);
    LearningPreferenceResponse updatePreference(Long userId, LearningPreferenceRequest request);
}
