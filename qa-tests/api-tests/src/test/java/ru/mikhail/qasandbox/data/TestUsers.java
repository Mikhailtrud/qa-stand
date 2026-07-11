package ru.mikhail.qasandbox.data;

import ru.mikhail.qasandbox.config.Config;
import ru.mikhail.qasandbox.dto.request.LoginRequest;

public final class TestUsers {

    private TestUsers() {
    }

    public static LoginRequest admin() {
        return new LoginRequest(
                Config.getAdminEmail(),
                Config.getAdminPassword()
        );
    }
}