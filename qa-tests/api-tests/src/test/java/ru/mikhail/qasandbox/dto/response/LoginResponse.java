package ru.mikhail.qasandbox.dto.response;

public record LoginResponse(
        String token,
        String role
) {
}