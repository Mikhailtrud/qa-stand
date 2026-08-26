package ru.mikhail.qasandbox.tests.usersTests;

import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.CreatedUserTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.request.LoginRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;
import ru.mikhail.qasandbox.dto.response.LoginResponse;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateUserTest extends CreatedUserTest {

    @Test
    void updateUserNameEmailAndRole() {
        CreateUsersResponse created = createUser(UserBuilder.validUser().build());
        EditUserRequest update = EditUserBuilder.validUser()
                .withName("Updated Name")
                .withRole("ADMIN")
                .build();

        ApiResponse<EditUserResponse> response = usersClient.editUser(created.id(), update);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body())
                .returns("Updated Name", EditUserResponse::name)
                .returns(update.email(), EditUserResponse::email)
                .returns("ADMIN", EditUserResponse::role);

        ApiResponse<GetUserResponse> getResponse = usersClient.getUserById(created.id());
        assertThat(getResponse.statusCode()).isEqualTo(200);
        assertThat(getResponse.body().email()).isEqualTo(update.email());
    }

    @Test
    void updateUserWithoutPasswordKeepsExistingPassword() {
        String originalPassword = "KeepPass1";
        CreateUsersRequest create = UserBuilder.validUser()
                .withPassword(originalPassword)
                .build();
        CreateUsersResponse created = createUser(create);
        EditUserRequest update = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> updateResponse = usersClient.editUser(created.id(), update);
        ApiResponse<LoginResponse> loginResponse = authClient.login(
                new LoginRequest(update.email(), originalPassword)
        );

        assertThat(updateResponse.statusCode()).isEqualTo(200);
        assertThat(loginResponse.statusCode()).isEqualTo(200);
        assertThat(loginResponse.body().token()).isNotBlank();
    }

    @Test
    void updateUserWithInvalidPasswordReturns400() {
        CreateUsersResponse created = createUser(UserBuilder.validUser().build());
        EditUserRequest update = EditUserBuilder.validUser()
                .withPassword("abc")
                .build();

        ApiResponse<EditUserResponse> response = usersClient.editUser(created.id(), update);

        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.errorBody().errors()).containsKey("password");
    }
}
