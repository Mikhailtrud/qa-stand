package ru.mikhail.qasandbox.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.config.Config;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

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
        assertThat(response.email()).isEqualTo(Config.getUserEmail());
        assertThat(response.name()).isEqualTo(Config.userName);
        assertThat(response.role()).isEqualTo(Config.getUserRole());
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            usersClient.deleteUser(idToRemove);
        }
    }
}