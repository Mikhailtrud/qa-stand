package ru.mikhail.qasandbox.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.BaseTest;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;

public class DeleteUsersTests extends BaseTest {
    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        String token = authClient.login(TestUsers.admin()).token();
        usersClient.setToken(token);

        CreateUsersResponse response =
                usersClient.createUser(TestUsers.user());
        idToRemove = response.id();
    }

    @Test
    void userShouldBeDeleted() {
        Allure.step("Delete user success");
        usersClient.deleteUser(idToRemove);
    }

}