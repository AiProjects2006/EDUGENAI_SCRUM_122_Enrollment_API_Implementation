package com.edugenai.enrollment.controller;
import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.EnrollmentHistory;
import com.edugenai.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(@Valid @RequestBody CourseEnrollmentRequest request) {
        return new ResponseEntity<>(enrollmentService.createEnrollment(request), HttpStatus.CREATED);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(enrollmentId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{enrollmentId}/history")
    public ResponseEntity<List<EnrollmentHistory>> getEnrollmentHistory(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentHistory(enrollmentId));
    }
}
