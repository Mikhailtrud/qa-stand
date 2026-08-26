package com.qasandbox.backend.event;

import com.qasandbox.backend.entity.enums.UserRole;

import java.time.Instant;

public record UserEvent(
        String eventType,
        Long userId,
        String email,
        UserRole role,
        Instant timestamp
) {
}
