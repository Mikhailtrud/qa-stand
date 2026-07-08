package com.qasandbox.backend.dto.auth;

import com.qasandbox.backend.entity.enums.UserRole;

public record AuthResponse(

        String token,

        UserRole role

) {
}