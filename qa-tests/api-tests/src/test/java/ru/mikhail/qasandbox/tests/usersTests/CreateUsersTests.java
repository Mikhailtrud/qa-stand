package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.config.UserDataConfig;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUsersTests extends BaseTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        String token = authClient.login(TestUsers.admin()).token();
        usersClient.setToken(token);

        idToRemove = null;
    }

    @Test
    void userShouldBeCreated() {
        Allure.step("Create user success");

        CreateUsersResponse response =
                usersClient.createUser(TestUsers.user());

        assertThat(response)
                .isNotNull();

        assertThat(response.id()).isNotNull().isPositive();
        assertThat(response.email()).isEqualTo(UserDataConfig.getUserEmail());
        assertThat(response.name()).isEqualTo(UserDataConfig.USER_NAME);
        assertThat(response.role()).isEqualTo(UserDataConfig.getUserRole());

        idToRemove = response.id();
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            usersClient.deleteUser(idToRemove);
        }
    }
}