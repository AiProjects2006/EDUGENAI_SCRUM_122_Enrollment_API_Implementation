package com.edugenai.enrollment.controller;
import com.edugenai.enrollment.dto.request.CourseEnrollmentRequest;
import com.edugenai.enrollment.dto.response.EnrollmentResponse;
import com.edugenai.enrollment.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(@RequestBody CourseEnrollmentRequest request) {
        return new ResponseEntity<>(enrollmentService.createEnrollment(request), HttpStatus.CREATED);
    }
}
