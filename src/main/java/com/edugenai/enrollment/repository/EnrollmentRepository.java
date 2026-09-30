package com.edugenai.enrollment.repository;
import com.edugenai.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {}
