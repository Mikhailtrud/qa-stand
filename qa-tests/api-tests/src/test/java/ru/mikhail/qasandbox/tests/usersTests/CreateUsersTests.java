package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUsersTests extends AuthenticatedTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        idToRemove = null;
    }

    @Test
    void userShouldBeCreated() {
        Allure.step("Create user success");

        ApiResponse<CreateUsersResponse> response =
                usersClient.createUser(TestUsers.user());

        assertThat(response.statusCode()).isEqualTo(201);

        assertThat(response.body().id()).isNotNull().isPositive();
        assertThat(response.body().email()).isEqualTo(TestUsers.user().email());
        assertThat(response.body().name()).isEqualTo(TestUsers.user().name());
        assertThat(response.body().role()).isEqualTo(TestUsers.user().role());

        idToRemove = response.body().id();
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            usersClient.deleteUser(idToRemove);
        }
    }
}