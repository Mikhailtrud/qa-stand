package ru.mikhail.qasandbox.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.config.Config;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

public class EditUsersTests extends BaseTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        String token = authClient.login(TestUsers.admin()).token();
        usersClient.setToken(token);

        CreateUsersResponse response = usersClient.createUser(TestUsers.user());
        idToRemove = response.id();
        System.out.println(idToRemove);
        System.out.println(token);
    }

    @Test
    void userShouldBeEdited() {
        Allure.step("Edit user success");
        EditUserResponse response =
                usersClient.editUser(idToRemove, TestUsers.userEdit());

        assertThat(response)
                .isNotNull();

        assertThat(response.id()).isEqualTo(idToRemove);
        assertThat(response.id()).isNotNull().isPositive();
        assertThat(response.name()).isEqualTo(Config.editUserName);
        assertThat(response.email()).isEqualTo(Config.editUserEmail());
        assertThat(response.role()).isEqualTo(Config.editUserRole());
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            usersClient.deleteUser(idToRemove);
        }
    }

}