package com.qasandbox.backend.controller;

import com.qasandbox.backend.dto.auth.AuthRequest;
import com.qasandbox.backend.dto.auth.AuthResponse;
import com.qasandbox.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody AuthRequest request
    ) {
        return authService.login(request);
    }

}