package com.edugenai.enrollment.service.impl;
import com.edugenai.enrollment.dto.request.LearningPreferenceRequest;
import com.edugenai.enrollment.dto.response.LearningPreferenceResponse;
import com.edugenai.enrollment.entity.LearningPreference;
import com.edugenai.enrollment.exception.ResourceNotFoundException;
import com.edugenai.enrollment.repository.LearningPreferenceRepository;
import com.edugenai.enrollment.service.LearningPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningPreferenceServiceImpl implements LearningPreferenceService {
    private final LearningPreferenceRepository preferenceRepository;

    @Override
    public LearningPreferenceResponse createPreference(LearningPreferenceRequest request) {
        if (preferenceRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("Preferences already exist for this user. Please use update instead.");
        }

        LearningPreference preference = LearningPreference.builder()
                .userId(request.getUserId())
                .learningStyle(request.getLearningStyle())
                .difficultyLevel(request.getDifficultyLevel())
                .build();
        
        LearningPreference saved = preferenceRepository.save(preference);
        return mapToResponse(saved);
    }

    @Override
    public LearningPreferenceResponse getPreferenceByUserId(Long userId) {
        LearningPreference preference = preferenceRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No preferences found for user: " + userId));
        return mapToResponse(preference);
    }

    @Override
    public LearningPreferenceResponse updatePreference(Long userId, LearningPreferenceRequest request) {
        LearningPreference preference = preferenceRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No preferences found for user: " + userId));
        
        preference.setLearningStyle(request.getLearningStyle());
        preference.setDifficultyLevel(request.getDifficultyLevel());
        
        LearningPreference updated = preferenceRepository.save(preference);
        return mapToResponse(updated);
    }

    private LearningPreferenceResponse mapToResponse(LearningPreference preference) {
        return LearningPreferenceResponse.builder()
                .preferenceId(preference.getPreferenceId())
                .userId(preference.getUserId())
                .learningStyle(preference.getLearningStyle())
                .difficultyLevel(preference.getDifficultyLevel())
                .build();
    }
}
