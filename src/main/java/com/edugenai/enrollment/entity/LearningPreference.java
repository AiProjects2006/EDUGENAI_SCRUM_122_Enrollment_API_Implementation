package com.edugenai.enrollment.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "learning_preference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preferenceId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "learning_style", nullable = false)
    private String learningStyle;

    @Column(name = "difficulty_level", nullable = false)
    private String difficultyLevel;
}
