package com.edugenai.enrollment.entity;
import com.edugenai.enrollment.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @CreationTimestamp
    @Column(name = "enrollment_date", nullable = false, updatable = false)
    private LocalDateTime enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private EnrollmentStatus status;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_email")
    private String studentEmail;

    @Column(name = "student_grade")
    private String studentGrade;

    @Column(name = "student_avatar", length = 200000)
    private String studentAvatar;

    @Column(name = "request_message", length = 2000)
    private String requestMessage;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "grade_level")
    private String gradeLevel;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "creator_email")
    private String creatorEmail;

    @Column(name = "rejection_reason", length = 1000)
    private String rejectionReason;

    @Column(name = "responded_at")
    private LocalDateTime respondedAt;

    @Column(name = "last_accessed")
    private LocalDateTime lastAccessed;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
