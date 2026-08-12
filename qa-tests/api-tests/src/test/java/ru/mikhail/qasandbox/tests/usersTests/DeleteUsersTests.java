package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.TestUsers;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteUsersTests extends AuthenticatedTest {

    private Integer idToRemove;

    @BeforeEach
    void setUpTest() {
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(TestUsers.user());
        idToRemove = response.body().id();
    }

    @Test
    void userShouldBeDeleted() {
        Allure.step("Delete user success");

        ApiResponse<Void> response =
                usersClient.deleteUser(idToRemove);

        assertThat(response.statusCode()).isEqualTo(200);
    }
}