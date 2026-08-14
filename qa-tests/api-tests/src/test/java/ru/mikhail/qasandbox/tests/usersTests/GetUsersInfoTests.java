package ru.mikhail.qasandbox.tests.usersTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.GetUsersResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetUsersInfoTests extends AuthenticatedTest {

    private Integer idToRemove;
    private CreateUsersRequest request;

    @BeforeEach
    void setUpTest() {
        request = UserBuilder.validUser().build();
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(request);

        assertThat(response.statusCode()).isEqualTo(201);

        idToRemove = response.body().id();
    }

    @Test
    void shouldGetUsersInfo() {
        ApiResponse<GetUsersResponse[]> response = Allure.step(
                "Get users",
                () -> usersClient.getUsersResponse()
        );

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isNotEmpty();

        assertThat(response.body())
                .anySatisfy(user -> {
                    assertThat(user.id()).isEqualTo(idToRemove);
                    assertThat(user.email()).isEqualTo(request.email());
                    assertThat(user.name()).isEqualTo(request.name());
                    assertThat(user.role()).isEqualTo(request.role());
                });
    }

    @AfterEach
    void cleanUp() {
        if (idToRemove != null) {
            ApiResponse<Void> response =
                    usersClient.deleteUser(idToRemove);

            assertThat(response.statusCode()).isIn(200);
        }
    }
}
