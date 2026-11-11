package com.edugenai.enrollment.dto.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LearningPreferenceResponse {
    private Long preferenceId;
    private Long userId;
    private String learningStyle;
    private String difficultyLevel;
}
