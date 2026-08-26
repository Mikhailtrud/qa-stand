package ru.mikhail.qasandbox.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EditUserRequest(
        String name,
        String email,
        String role,
        String password
) {
    public EditUserRequest(String name, String email, String role) {
        this(name, email, role, null);
    }
}
