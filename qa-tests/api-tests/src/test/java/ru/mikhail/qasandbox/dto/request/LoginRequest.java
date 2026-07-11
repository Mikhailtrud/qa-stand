package ru.mikhail.qasandbox.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}