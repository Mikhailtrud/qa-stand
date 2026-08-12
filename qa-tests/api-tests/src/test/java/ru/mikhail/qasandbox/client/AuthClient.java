package ru.mikhail.qasandbox.client;

import ru.mikhail.qasandbox.dto.request.LoginRequest;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

public class AuthClient extends BaseApiClient {

    private static final String LOGIN_ENDPOINT = "/auth/login";

    public ApiResponse<LoginResponse> login(LoginRequest request) {
        return post(
                LOGIN_ENDPOINT,
                request,
                LoginResponse.class
        );
    }
}