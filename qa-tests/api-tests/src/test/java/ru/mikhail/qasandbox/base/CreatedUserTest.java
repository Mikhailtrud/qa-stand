package ru.mikhail.qasandbox.base;

import org.junit.jupiter.api.AfterEach;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class CreatedUserTest extends AuthenticatedTest {

    private final Set<Integer> userIdsToRemove = new LinkedHashSet<>();

    protected CreateUsersResponse createUser(CreateUsersRequest request) {
        ApiResponse<CreateUsersResponse> response = usersClient.createUser(request);
        assertThat(response.statusCode()).isEqualTo(201);
        userIdsToRemove.add(response.body().id());
        return response.body();
    }

    protected void markDeleted(Integer userId) {
        userIdsToRemove.remove(userId);
    }

    @AfterEach
    void removeCreatedUsers() {
        for (Integer userId : userIdsToRemove) {
            ApiResponse<Void> response = usersClient.deleteUser(userId);
            assertThat(response.statusCode()).isIn(200, 404);
        }
        userIdsToRemove.clear();
    }
}
