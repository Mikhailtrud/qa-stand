package ru.mikhail.qasandbox.data;

import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.request.LoginRequest;

public final class TestUsers {

    private TestUsers() {
    }

    public static LoginRequest admin() {
        return new LoginRequest(
                UserDataConfig.getAdminEmail(),
                UserDataConfig.getAdminPassword()
        );
    }

    public static CreateUsersRequest user() {
        return new CreateUsersRequest(
                UserDataConfig.USER_NAME,
                UserDataConfig.getUserEmail(),
                UserDataConfig.getUserPassword(),
                UserDataConfig.getUserRole()
        );
    }

    public static EditUserRequest userEdit() {
        return new EditUserRequest(
                UserDataConfig.EDIT_USER_NAME,
                UserDataConfig.getEditUserEmail(),
                UserDataConfig.getEditUserRole()
        );
    }
}