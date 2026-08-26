package ru.mikhail.qasandbox.dto.response;

public record AuditEventResponse(
        Long id,
        String eventType,
        Integer userId,
        String email,
        String role,
        String eventTime,
        String receivedAt
) {
}
