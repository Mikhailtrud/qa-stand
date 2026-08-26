package com.qasandbox.backend.dto.user;

import com.qasandbox.backend.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateUserRequest(

        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @NotNull(message = "Role is required")
        UserRole role,

        @Pattern(
                regexp = "^\\s*$|^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
                message = "Password must be at least 8 characters and contain at least one letter and one digit"
        )
        String password

) {
}
