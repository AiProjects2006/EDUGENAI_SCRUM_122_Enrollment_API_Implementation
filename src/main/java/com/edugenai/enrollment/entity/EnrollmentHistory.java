package com.edugenai.enrollment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "enrollment_id", nullable = false)
    private Long enrollmentId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "changed_by")
    private String changedBy;

    @CreationTimestamp
    @Column(name = "changed_date", nullable = false, updatable = false)
    private LocalDateTime changedDate;
}
