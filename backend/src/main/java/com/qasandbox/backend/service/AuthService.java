package com.qasandbox.backend.service;

import com.qasandbox.backend.dto.auth.AuthRequest;
import com.qasandbox.backend.dto.auth.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest request);

}