package ru.mikhail.qasandbox.tests.integrationTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.CreatedUserTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.client.AuditServiceClient;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.CreateUsersRequest;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;
import ru.mikhail.qasandbox.redis.RedisTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mikhail.qasandbox.assertions.AuditAwait.awaitUserEvent;

class UserLifecycleIntegrationTest extends CreatedUserTest {

    private final AuditServiceClient auditServiceClient = new AuditServiceClient();
    private RedisTestClient redis;

    @BeforeEach
    void connectRedis() {
        redis = new RedisTestClient();
    }

    @AfterEach
    void closeRedis() {
        redis.close();
    }

    @Test
    void userLifecycleAcrossApiCacheAndAudit() {
        CreateUsersRequest create = UserBuilder.validUser().build();
        CreateUsersResponse created = createUser(create);

        getUserAndAssert(created.id(), create.email(), create.name(), create.role());
        assertThat(redis.userExists(created.id())).isTrue();
        awaitUserEvent(auditServiceClient, "USER_CREATED", created.id(), create.email());

        EditUserRequest update = EditUserBuilder.validUser()
                .withName("Lifecycle Updated")
                .withRole("ADMIN")
                .build();
        updateUserAndAssert(created.id(), update);
        assertThat(redis.userExists(created.id())).isFalse();

        getUserAndAssert(created.id(), update.email(), update.name(), update.role());
        assertThat(redis.userExists(created.id())).isTrue();
        assertThat(redis.getUser(created.id())).contains(update.email());
        awaitUserEvent(auditServiceClient, "USER_UPDATED", created.id(), update.email());

        ApiResponse<Void> deleteResponse = usersClient.deleteUser(created.id());
        assertThat(deleteResponse.statusCode()).isEqualTo(200);
        markDeleted(created.id());
        assertThat(usersClient.getUserById(created.id()).statusCode()).isEqualTo(404);
        assertThat(redis.userExists(created.id())).isFalse();
    }

    private void updateUserAndAssert(Integer id, EditUserRequest request) {
        ApiResponse<EditUserResponse> response = usersClient.editUser(id, request);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().email()).isEqualTo(request.email());
    }

    private void getUserAndAssert(Integer id, String email, String name, String role) {
        ApiResponse<GetUserResponse> response = usersClient.getUserById(id);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body())
                .returns(email, GetUserResponse::email)
                .returns(name, GetUserResponse::name)
                .returns(role, GetUserResponse::role);
    }
}
