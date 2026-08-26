package ru.mikhail.qasandbox.tests.integrationTests;

import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.CreatedUserTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.client.AuditServiceClient;
import ru.mikhail.qasandbox.data.builder.EditUserBuilder;
import ru.mikhail.qasandbox.data.builder.UserBuilder;
import ru.mikhail.qasandbox.dto.request.EditUserRequest;
import ru.mikhail.qasandbox.dto.response.CreateUsersResponse;
import ru.mikhail.qasandbox.dto.response.EditUserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mikhail.qasandbox.assertions.AuditAwait.awaitUserEvent;

class UserAuditIntegrationTest extends CreatedUserTest {

    private final AuditServiceClient auditServiceClient = new AuditServiceClient();

    @Test
    void createUserProducesCreatedAuditEvent() {
        CreateUsersResponse created = createUser(UserBuilder.validUser().build());

        awaitUserEvent(auditServiceClient, "USER_CREATED", created.id(), created.email());
    }

    @Test
    void updateUserProducesUpdatedAuditEvent() {
        CreateUsersResponse created = createUser(UserBuilder.validUser().build());
        EditUserRequest update = EditUserBuilder.validUser().build();

        ApiResponse<EditUserResponse> response = usersClient.editUser(created.id(), update);

        assertThat(response.statusCode()).isEqualTo(200);
        awaitUserEvent(auditServiceClient, "USER_UPDATED", created.id(), update.email());
    }
}
