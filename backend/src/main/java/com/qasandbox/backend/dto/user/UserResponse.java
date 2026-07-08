package com.qasandbox.backend.dto.user;

import com.qasandbox.backend.entity.enums.UserRole;

public record UserResponse(

        Long id,

        String email,

        String name,

        UserRole role

) {
}