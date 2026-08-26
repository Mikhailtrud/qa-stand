package com.qasandbox.backend.dto.external;

public record ExternalProfileResponse(
        Long userId,
        String status,
        int score
) {
}
