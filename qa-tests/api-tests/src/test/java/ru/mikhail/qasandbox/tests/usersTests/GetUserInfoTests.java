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
import static ru.mikhail.qasandbox.assertions.ApiResponseAssert.assertThat;

public class GetUserInfoTests extends AuthenticatedTest {

    private Integer idToRemove;
    private CreateUsersRequest request;

    @BeforeEach
    void setUpTest() {
        request = UserBuilder.validUser().build();
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(request);

        assertThat(response)
                .hasStatusCode(201);

        idToRemove = response.body().id();
    }

    @Test
    void shouldGetUserInfo() {
        ApiResponse<GetUserResponse> response = Allure.step(
                "Get a user",
                () -> usersClient.getUserById(idToRemove)
        );

        assertThat(response)
                .hasStatusCode(200);

        GetUserResponse body = response.body();

        assertThat(body.id()).isEqualTo(idToRemove);
        assertThat(body.name()).isEqualTo(request.name());
        assertThat(body.email()).isEqualTo(request.email());
        assertThat(body.role()).isEqualTo(request.role());
    }

    @Test
    void shouldNotGetUserInfoWithInvalidId() {
        Integer deletedUserId = idToRemove;

        ApiResponse<Void> deleteResponse =
                usersClient.deleteUser(deletedUserId);

        assertThat(deleteResponse)
                .hasStatusCode(200);

        ApiResponse<GetUserResponse> response = Allure.step(
                "Get a user with an invalid id",
                () -> usersClient.getUserById(deletedUserId)
        );

        assertThat(response)
                .hasStatusCode(404);
    }

    @Test
    void shouldNotGetUserInfoWithStringId() {
        ApiResponse<Void> response = Allure.step(
                "Get a user with a string id",
                () -> usersClient.getUserByRawId("abc")
        );

        assertThat(response)
                .hasStatusCode(400);
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
