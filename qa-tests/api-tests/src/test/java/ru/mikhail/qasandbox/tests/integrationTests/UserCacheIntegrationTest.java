package ru.mikhail.qasandbox.tests.integrationTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.CreatedUserTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;
import ru.mikhail.qasandbox.dto.response.GetUserResponse;
import ru.mikhail.qasandbox.redis.RedisTestClient;

import static org.assertj.core.api.Assertions.assertThat;

class UserCacheIntegrationTest extends CreatedUserTest {

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
    void getUserCachesUserInRedis() {
        CreateUsersResponse created = createUser(UserBuilder.validUser().build());

        ApiResponse<GetUserResponse> response = usersClient.getUserById(created.id());

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(redis.userExists(created.id())).isTrue();
        assertThat(redis.getUser(created.id())).contains(created.email());
    }

    @Test
    void updateUserInvalidatesCachedUser() {
        CreateUsersResponse created = createUser(UserBuilder.validUser().build());
        usersClient.getUserById(created.id());
        assertThat(redis.userExists(created.id())).isTrue();
        EditUserRequest update = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> response = usersClient.editUser(created.id(), update);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(redis.userExists(created.id())).isFalse();
    }
}
