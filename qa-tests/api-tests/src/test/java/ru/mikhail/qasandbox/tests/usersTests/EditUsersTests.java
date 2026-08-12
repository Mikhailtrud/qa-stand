package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class EditUsersTests extends AuthenticatedTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(TestUsers.user());
        idToRemove = response.body().id();
    }

    @Test
    void userShouldBeEdited() {
        Allure.step("Edit user success");

        ApiResponse<EditUserResponse> response =
                usersClient.editUser(idToRemove, TestUsers.userEdit());

        assertThat(response.statusCode()).isEqualTo(200);

        assertThat(response.body().id()).isEqualTo(idToRemove);
        assertThat(response.body().name()).isEqualTo(TestUsers.userEdit().name());
        assertThat(response.body().email()).isEqualTo(TestUsers.userEdit().email());
        assertThat(response.body().role()).isEqualTo(TestUsers.userEdit().role());
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            usersClient.deleteUser(idToRemove);
        }
    }

}