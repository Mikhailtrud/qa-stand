package com.qasandbox.backend.dto.user;

import com.qasandbox.backend.entity.enums.UserRole;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void acceptsPasswordWithEightCharactersLetterAndDigit() {
        CreateUserRequest request = new CreateUserRequest("Admin", "test@email.com", "admin123", UserRole.ADMIN);

        assertThat(validator.validate(request)).isEmpty();
    }

    @Test
    void rejectsShortPasswordAndPasswordWithoutDigit() {
        CreateUserRequest shortPassword = new CreateUserRequest("User", "user@email.com", "a1", UserRole.USER);
        CreateUserRequest noDigit = new CreateUserRequest("User", "user@email.com", "abcdefgh", UserRole.USER);

        assertThat(validator.validate(shortPassword))
                .anyMatch(error -> error.getPropertyPath().toString().equals("password"));
        assertThat(validator.validate(noDigit))
                .anyMatch(error -> error.getPropertyPath().toString().equals("password"));
    }

    @Test
    void permitsBlankPasswordOnlyForUpdate() {
        UpdateUserRequest request = new UpdateUserRequest("User", "user@email.com", UserRole.USER, "");

        assertThat(validator.validate(request)).isEmpty();
    }
}
