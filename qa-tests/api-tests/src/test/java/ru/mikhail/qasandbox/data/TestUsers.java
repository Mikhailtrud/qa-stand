package ru.mikhail.qasandbox.data;

import ru.mikhail.qasandbox.config.Config;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
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

    public static CreateUsersRequest user() {
        return new CreateUsersRequest(
                Config.userName,
                Config.getUserEmail(),
                Config.getUserPassword(),
                Config.getUserRole()
        );
    }

    public static EditUserRequest userEdit() {
        return new EditUserRequest(
                Config.editUserName,
                Config.editUserEmail(),
                Config.editUserRole()
        );
    }
}