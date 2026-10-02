package com.edugenai.enrollment.service.impl;
import com.edugenai.enrollment.dto.request.LearningPreferenceRequest;
import com.edugenai.enrollment.dto.response.LearningPreferenceResponse;
import com.edugenai.enrollment.entity.LearningPreference;
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

        return LearningPreferenceResponse.builder()
                .preferenceId(saved.getPreferenceId())
                .userId(saved.getUserId())
                .learningStyle(saved.getLearningStyle())
                .difficultyLevel(saved.getDifficultyLevel())
                .build();
    }
}
