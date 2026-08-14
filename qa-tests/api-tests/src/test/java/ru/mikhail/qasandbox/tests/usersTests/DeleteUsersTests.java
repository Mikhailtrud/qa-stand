package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteUsersTests extends AuthenticatedTest {

    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        CreateUsersRequest request = UserBuilder.validUser().build();
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(request);

        assertThat(response.statusCode()).isEqualTo(201);

        idToRemove = response.body().id();
    }

    @Test
    void userShouldBeDeleted() {
        Integer deletedUserId = idToRemove;

        ApiResponse<Void> response = Allure.step(
                "Delete a user",
                () -> usersClient.deleteUser(deletedUserId)
        );

        assertThat(response.statusCode()).isEqualTo(200);

        ApiResponse<GetUserResponse> getResponse =
                usersClient.getUserById(deletedUserId);

        assertThat(getResponse.statusCode()).isEqualTo(404);
    }

    @Test
    void userShouldNotBeDeletedWithNotExistingId() {
        Integer deletedUserId = idToRemove;

        ApiResponse<Void> responseDelete = usersClient.deleteUser(deletedUserId);

        assertThat(responseDelete.statusCode()).isEqualTo(200);

        ApiResponse<Void> response = Allure.step(
                "Delete a user with a non-existing id",
                () -> usersClient.deleteUser(deletedUserId)
        );

        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    void userShouldNotBeDeletedWithStringId() {
        ApiResponse<Void> response = Allure.step(
                "Delete a user with a string id",
                () -> usersClient.deleteUserByRawId("abc")
        );

        assertThat(response.statusCode()).isEqualTo(400);
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
