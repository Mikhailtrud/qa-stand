package ru.mikhail.qasandbox.assertions;

import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.client.AuditServiceClient;
import ru.mikhail.qasandbox.dto.response.AuditEventResponse;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public final class AuditAwait {

    private AuditAwait() {
    }

    public static void awaitUserEvent(
            AuditServiceClient client,
            String eventType,
            Integer userId,
            String email
    ) {
        await("audit event " + eventType + " for user " + userId)
                .atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofMillis(250))
                .untilAsserted(() -> {
                    ApiResponse<AuditEventResponse[]> response = client.getEvents();
                    assertThat(response.statusCode()).isEqualTo(200);
                    assertThat(response.body()).anySatisfy(event -> {
                        assertThat(event.eventType()).isEqualTo(eventType);
                        assertThat(event.userId()).isEqualTo(userId);
                        assertThat(event.email()).isEqualTo(email);
                    });
                });
    }
}
