package ru.mikhail.qasandbox.dto.response;

public record CreateUsersResponse(
        Integer id,
        String email,
        String name,
        String role
) {
}