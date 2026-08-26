package ru.mikhail.qasandbox.tests.usersTests;

import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.CreatedUserTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

class UserPasswordValidationTest extends CreatedUserTest {

    @Test
    void createUserWithValidPassword() {
        CreateUsersRequest request = UserBuilder.validUser()
                .withPassword("Test1234")
                .build();

        CreateUsersResponse created = createUser(request);

        assertThat(created.email()).isEqualTo(request.email());
    }

    @Test
    void createUserWithTooShortPassword() {
        assertInvalidPassword("a1");
    }

    @Test
    void createUserWithoutDigit() {
        assertInvalidPassword("OnlyLetters");
    }

    @Test
    void createUserWithoutLetter() {
        assertInvalidPassword("12345678");
    }

    private void assertInvalidPassword(String password) {
        CreateUsersRequest request = UserBuilder.validUser()
                .withPassword(password)
                .build();

        ApiResponse<CreateUsersResponse> response = usersClient.createUser(request);

        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.errorBody().message()).isEqualTo("Validation failed");
        assertThat(response.errorBody().errors()).containsKey("password");
        assertThat(response.errorBody().errors().get("password"))
                .contains("at least 8 characters");
    }
}
