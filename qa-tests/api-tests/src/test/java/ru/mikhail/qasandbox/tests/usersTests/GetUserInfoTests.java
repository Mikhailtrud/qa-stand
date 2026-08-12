package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUserInfoTests extends BaseTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        String token = authClient.login(TestUsers.admin()).token();
        usersClient.setToken(token);

        CreateUsersResponse response =
                usersClient.createUser(TestUsers.user());
        idToRemove = response.id();
    }

    @Test
    void shouldGetUserInfo() {
        Allure.step("Get user info");

        GetUserResponse response =
                usersClient.getUserById(idToRemove);

        assertThat(response).isNotNull();
        assertThat(response.id()).isNotNull().isPositive();
        assertThat(response.email()).isEqualTo(UserDataConfig.getUserEmail());
        assertThat(response.name()).isEqualTo(UserDataConfig.USER_NAME);
        assertThat(response.role()).isEqualTo(UserDataConfig.getUserRole());
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            usersClient.deleteUser(idToRemove);
        }
    }
}