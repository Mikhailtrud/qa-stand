package com.qastand.audit.event;

import java.time.Instant;

public record UserEvent(
        String eventType,
        Long userId,
        String email,
        String role,
        Instant timestamp
) {
}
