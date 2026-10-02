package com.edugenai.enrollment.repository;
import com.edugenai.enrollment.entity.LearningPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LearningPreferenceRepository extends JpaRepository<LearningPreference, Long> {
    Optional<LearningPreference> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
