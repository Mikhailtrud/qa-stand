package com.qasandbox.backend.service.impl;

import com.qasandbox.backend.dto.auth.AuthRequest;
import com.qasandbox.backend.dto.auth.AuthResponse;
import com.qasandbox.backend.entity.User;
import com.qasandbox.backend.exception.ApiException;
import com.qasandbox.backend.repository.UserRepository;
import com.qasandbox.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new ApiException(
                                HttpStatus.UNAUTHORIZED,
                                "Invalid credentials"
                        )
                );

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApiException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid credentials"
            );
        }

        String token = user.getRole().name().toLowerCase() + "-token";

        return new AuthResponse(
                token,
                user.getRole()
        );
    }
}