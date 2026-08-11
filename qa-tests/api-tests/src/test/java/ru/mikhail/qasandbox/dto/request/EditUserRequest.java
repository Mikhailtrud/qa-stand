package ru.mikhail.qasandbox.dto.request;

public record EditUserRequest(
        String name,
        String email,
        String role
) {
}