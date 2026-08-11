package ru.mikhail.qasandbox.dto.response;

public record GetUserResponse(
        Integer id,
        String email,
        String name,
        String role
) {
}