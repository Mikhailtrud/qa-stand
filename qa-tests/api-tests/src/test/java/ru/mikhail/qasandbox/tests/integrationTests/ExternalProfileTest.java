package ru.mikhail.qasandbox.tests.integrationTests;

import org.junit.jupiter.api.Test;
import ru.mikhail.qasandbox.base.AuthenticatedTest;
import ru.mikhail.qasandbox.client.ApiResponse;
import ru.mikhail.qasandbox.dto.response.ExternalProfileResponse;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalProfileTest extends AuthenticatedTest {

    private static final int SUCCESS_SCENARIO_ID = 1;
    private static final int NOT_FOUND_SCENARIO_ID = 404;
    private static final int SERVICE_ERROR_SCENARIO_ID = 500;
    private static final int TIMEOUT_SCENARIO_ID = 999;

    @Test
    void getExternalProfileSuccess() {
        ApiResponse<ExternalProfileResponse> response =
                usersClient.getExternalProfile(SUCCESS_SCENARIO_ID);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body())
                .returns(SUCCESS_SCENARIO_ID, ExternalProfileResponse::userId)
                .returns("ACTIVE", ExternalProfileResponse::status)
                .returns(85, ExternalProfileResponse::score);
    }

    @Test
    void getExternalProfileNotFound() {
        ApiResponse<ExternalProfileResponse> response =
                usersClient.getExternalProfile(NOT_FOUND_SCENARIO_ID);

        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(response.errorBody().message()).contains("not found");
    }

    @Test
    void getExternalProfileServiceError() {
        ApiResponse<ExternalProfileResponse> response =
                usersClient.getExternalProfile(SERVICE_ERROR_SCENARIO_ID);

        assertThat(response.statusCode()).isEqualTo(502);
        assertThat(response.errorBody().message()).contains("service failed");
    }

    @Test
    void getExternalProfileTimeout() {
        long startedAt = System.nanoTime();

        ApiResponse<ExternalProfileResponse> response =
                usersClient.getExternalProfile(TIMEOUT_SCENARIO_ID);

        long elapsedMillis = (System.nanoTime() - startedAt) / 1_000_000;
        assertThat(response.statusCode()).isEqualTo(504);
        assertThat(response.errorBody().message()).contains("timed out");
        assertThat(elapsedMillis).isLessThan(8_000);
    }
}
