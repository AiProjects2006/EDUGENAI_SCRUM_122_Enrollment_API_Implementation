package com.edugenai.enrollment.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LearningPreferenceRequest {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Learning style is required")
    private String learningStyle;

    @NotNull(message = "Difficulty level is required")
    private String difficultyLevel;
}
