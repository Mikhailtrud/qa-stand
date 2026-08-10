package ru.mikhail.qasandbox.dto.response;

public record GetUsersResponse(
        int id,
        String email,
        String name,
        String role
) {
}