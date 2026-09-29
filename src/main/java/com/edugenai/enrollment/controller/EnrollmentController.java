package com.edugenai.enrollment.controller;

import com.edugenai.enrollment.dto.request.GradeEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Operation(summary = "Create or update student grade enrollment")
    @PostMapping("/grade")
    public ResponseEntity<EnrollmentResponse> createOrUpdateEnrollment(
            @Valid @RequestBody GradeEnrollmentRequest request) {
        EnrollmentResponse response = enrollmentService.createOrUpdateEnrollment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
