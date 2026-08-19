package data.builder;

import data.testData.UserData;

import java.util.UUID;

import static data.testData.UserTestData.*;

public final class UserBuilder {

    private String name = VALID_NAME;
    private String email = "user-" + UUID.randomUUID() + "@test.local";
    private String password = VALID_PASSWORD;
    private String role = VALID_ROLE;

    private UserBuilder() {
    }

    public static UserBuilder user() {
        return new UserBuilder();
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

    public UserData build() {
        return new UserData(name, email, password, role);
    }
}
