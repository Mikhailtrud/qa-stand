package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.data.testData.UserTestData;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mikhail.qasandbox.assertions.ApiResponseAssert.assertThat;

public class CreateUsersTests extends AuthenticatedTest {

    private Integer idToRemove;

    @Test
    void userShouldBeCreated() {
        CreateUsersRequest request = UserBuilder.validUser().build();

        ApiResponse<CreateUsersResponse> response = Allure.step(
                "Create a user successfully",
                () -> usersClient.createUser(request)
        );

        assertThat(response)
                .hasStatusCode(201);

        CreateUsersResponse body = response.body();

        idToRemove = body.id();

        assertThat(body.id()).isNotNull().isPositive();
        assertThat(body.email()).isEqualTo(request.email());
        assertThat(body.name()).isEqualTo(request.name());
        assertThat(body.role()).isEqualTo(request.role());
    }

    @Test
    void userShouldNotBeCreatedWithInvalidEmail() {
        CreateUsersRequest request = UserBuilder.validUser()
                .withEmail(UserTestData.INVALID_EMAIL)
                .build();

        ApiResponse<CreateUsersResponse> response = Allure.step(
                "Create a user with an invalid email",
                () -> usersClient.createUser(request)
        );

        assertThat(response)
                .hasStatusCode(400)
                .hasErrorMessage("Validation failed");
    }

    @Test
    void userShouldNotBeCreatedWithInvalidPassword() {
        CreateUsersRequest request = UserBuilder.validUser()
                .withPassword(UserTestData.INVALID_PASSWORD)
                .build();

        ApiResponse<CreateUsersResponse> response = Allure.step(
                "Create a user with an invalid password",
                () -> usersClient.createUser(request)
        );

        assertThat(response)
                .hasStatusCode(400)
                .hasErrorMessage("Validation failed");
    }

    @Test
    void userShouldNotBeCreatedWithEmptyData() {
        CreateUsersRequest request = UserBuilder.validUser()
                .withName(UserTestData.EMPTY)
                .build();

        ApiResponse<CreateUsersResponse> response = Allure.step(
                "Create a user with empty data",
                () -> usersClient.createUser(request)
        );

        assertThat(response)
                .hasStatusCode(400)
                .hasErrorMessage("Validation failed");
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            ApiResponse<Void> response =
                    usersClient.deleteUser(idToRemove);

            assertThat(response)
                    .hasStatusCode(200);
        }
    }
}
