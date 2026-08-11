package ru.mikhail.qasandbox.dto.request;

public record CreateUsersRequest(
        String name,
        String email,
        String password,
        String role
) {
}