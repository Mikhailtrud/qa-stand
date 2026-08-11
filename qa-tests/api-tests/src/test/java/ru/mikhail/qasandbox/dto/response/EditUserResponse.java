package ru.mikhail.qasandbox.dto.response;

public record EditUserResponse(
        Integer id,
        String name,
        String email,
        String role
) {
}