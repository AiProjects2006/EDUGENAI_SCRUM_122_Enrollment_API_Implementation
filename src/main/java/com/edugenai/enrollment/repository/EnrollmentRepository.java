package com.edugenai.enrollment.repository;

import com.edugenai.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
    Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);
    List<Enrollment> findByCreatorIdOrderByCreateDateDesc(Long creatorId);
    List<Enrollment> findByCourseIdOrderByCreateDateDesc(Long courseId);
    List<Enrollment> findAllByOrderByCreateDateDesc();
}
