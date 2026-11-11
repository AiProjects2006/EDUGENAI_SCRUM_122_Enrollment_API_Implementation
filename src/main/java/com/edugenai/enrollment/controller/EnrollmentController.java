package com.edugenai.enrollment.controller;

import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.request.UpdateEnrollmentStatusRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.entity.EnrollmentHistory;
import com.edugenai.enrollment.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@CrossOrigin(origins = "*")
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

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByCreator(
            @PathVariable Long creatorId,
            @RequestParam(required = false) String creatorEmail,
            @RequestParam(required = false) String creatorName) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCreator(creatorId, creatorEmail, creatorName));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @PatchMapping("/{enrollmentId}/status")
    public ResponseEntity<EnrollmentResponse> patchEnrollmentStatus(
            @PathVariable Long enrollmentId,
            @Valid @RequestBody UpdateEnrollmentStatusRequest request) {
        return ResponseEntity.ok(enrollmentService.updateEnrollmentStatus(enrollmentId, request));
    }

    @PutMapping("/{enrollmentId}/status")
    public ResponseEntity<EnrollmentResponse> putEnrollmentStatus(
            @PathVariable Long enrollmentId,
            @Valid @RequestBody UpdateEnrollmentStatusRequest request) {
        return ResponseEntity.ok(enrollmentService.updateEnrollmentStatus(enrollmentId, request));
    }

    @GetMapping("/{enrollmentId}/history")
    public ResponseEntity<List<EnrollmentHistory>> getEnrollmentHistory(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentHistory(enrollmentId));
    }
}
