package ru.mikhail.qasandbox.data.builder;

import ru.mikhail.qasandbox.data.testData.UserTestData;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import java.util.UUID;

public final class UserBuilder {

    private String name;
    private String email;
    private String password;
    private String role;

    private UserBuilder() {
    }

    public static UserBuilder validUser() {
        return new UserBuilder()
                .withName(UserTestData.VALID_NAME)
                .withEmail("user+" + UUID.randomUUID() + "@email.com")
                .withPassword(UserTestData.VALID_PASSWORD)
                .withRole(UserTestData.VALID_ROLE);
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public CreateUsersRequest build() {
        return new CreateUsersRequest(name, email, password, role);
    }
}
