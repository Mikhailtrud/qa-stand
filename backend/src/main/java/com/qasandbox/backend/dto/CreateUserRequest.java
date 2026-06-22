package com.qasandbox.backend.dto;

public record CreateUserRequest(
        String username,
        String email
) {
}