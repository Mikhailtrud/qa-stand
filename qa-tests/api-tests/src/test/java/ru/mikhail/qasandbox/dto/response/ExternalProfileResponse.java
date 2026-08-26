package ru.mikhail.qasandbox.dto.response;

public record ExternalProfileResponse(
        Integer userId,
        String status,
        Integer score
) {
}
