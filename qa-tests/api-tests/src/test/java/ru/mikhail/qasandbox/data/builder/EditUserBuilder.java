package ru.mikhail.qasandbox.data.builder;

import java.util.UUID;

import ru.mikhail.qasandbox.data.testData.EditUserTestData;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;

public final class EditUserBuilder {

    private String name;
    private String email;
    private String role;

    private EditUserBuilder() {
    }

    public static EditUserBuilder validUser() {
        return new EditUserBuilder()
                .withName(EditUserTestData.VALID_NAME)
                .withEmail("user+" + UUID.randomUUID() + "@email.com")
                .withRole(EditUserTestData.VALID_ROLE);
    }

    public EditUserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EditUserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EditUserBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public EditUserRequest build() {
        return new EditUserRequest(name, email, role);
    }
}
