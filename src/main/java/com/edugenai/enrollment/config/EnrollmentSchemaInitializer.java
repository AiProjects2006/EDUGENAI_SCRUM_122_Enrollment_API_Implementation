package com.edugenai.enrollment.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnrollmentSchemaInitializer {

    private final JdbcTemplate jdbcTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void ensureStatusColumnSupportsPendingAndRejected() {
        try {
            jdbcTemplate.execute("ALTER TABLE enrollment MODIFY COLUMN status VARCHAR(32) NOT NULL");
            log.info("Verified enrollment.status column supports PENDING/ACTIVE/REJECTED workflow.");
        } catch (Exception ex) {
            log.debug("Enrollment schema column check skipped: {}", ex.getMessage());
        }
    }
}

