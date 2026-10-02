package com.edugenai.enrollment.repository;

import com.edugenai.enrollment.entity.EnrollmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentHistoryRepository extends JpaRepository<EnrollmentHistory, Long> {
    List<EnrollmentHistory> findByEnrollmentIdOrderByChangedDateDesc(Long enrollmentId);
}
