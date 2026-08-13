package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteUsersTests extends AuthenticatedTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        ApiResponse<CreateUsersResponse> response =
                usersClient.createUser(UserBuilder.validUser().build());
        assertThat(response.statusCode()).isEqualTo(201);
        idToRemove = response.body().id();
    }

    @Test
    void userShouldBeDeleted() {
        Allure.step("Delete user success");
        Integer deletedUserId = idToRemove;

        ApiResponse<Void> response =
                usersClient.deleteUser(idToRemove);
        assertThat(response.statusCode()).isEqualTo(200);

        ApiResponse<GetUserResponse> getResponse =
                usersClient.getUserById(deletedUserId);

        assertThat(getResponse.statusCode()).isEqualTo(404);
    }

    @Test
    void userShouldNotBeDeletedWithNotExistingId() {
        Allure.step("Delete user with not existing id");

        Integer deletedUserId = idToRemove;

        ApiResponse<Void> responseDelete =
                usersClient.deleteUser(idToRemove);
        assertThat(responseDelete.statusCode()).isEqualTo(200);

        ApiResponse<Void> response =
                usersClient.deleteUser(deletedUserId);
        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    void userShouldNotBeDeletedWithStringId() {
        Allure.step("Delete user with string id");

        ApiResponse<Void> response =
                usersClient.deleteUserByRawId("abc");

        assertThat(response.statusCode()).isEqualTo(403);
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            ApiResponse<Void> response =
                    usersClient.deleteUser(idToRemove);

            assertThat(response.statusCode()).isIn(200, 404);
        }
    }
}
