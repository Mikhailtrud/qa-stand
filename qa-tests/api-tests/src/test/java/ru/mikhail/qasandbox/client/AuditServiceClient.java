package ru.mikhail.qasandbox.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.mikhail.qasandbox.config.IntegrationConfig;
import ru.mikhail.qasandbox.dto.response.AuditEventResponse;
import ru.mikhail.qasandbox.specifications.Specifications;

public class AuditServiceClient {

    public ApiResponse<AuditEventResponse[]> getEvents() {
        Response response = RestAssured.given()
                .spec(Specifications.requestSpec(IntegrationConfig.getAuditServiceUrl()))
                .when()
                .get("/audit/events")
                .then()
                .log().ifValidationFails()
                .extract().response();

        return new ApiResponse<>(response, AuditEventResponse[].class);
    }
}
