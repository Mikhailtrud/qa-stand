package ru.mikhail.qasandbox.data.testData;

import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.dto.request.LoginRequest;

public final class AdminTestData {

    private AdminTestData() {
    }

    public static LoginRequest admin() {
        return new LoginRequest(
                UserDataConfig.getAdminEmail(),
                UserDataConfig.getAdminPassword()
        );
    }
}
