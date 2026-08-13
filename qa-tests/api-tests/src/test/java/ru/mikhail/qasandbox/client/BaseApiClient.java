package ru.mikhail.qasandbox.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.mikhail.qasandbox.specifications.Specifications;

public abstract class BaseApiClient {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    protected RequestSpecification request() {
        RequestSpecification request = RestAssured.given()
                .spec(Specifications.requestSpec());

        if (token != null && !token.isBlank()) {
            request.header("Authorization", "Bearer " + token);
        }

        return request;
    }

    protected <T> ApiResponse<T> get(
            String endpoint,
            Class<T> responseClass
    ) {
        Response response = request()
                .when()
                .get(endpoint)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();

        return new ApiResponse<>(response, responseClass);
    }

    protected <T> ApiResponse<T> post(
            String endpoint,
            Object requestBody,
            Class<T> responseClass
    ) {
        Response response = request()
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();

        return new ApiResponse<>(response, responseClass);
    }

    protected ApiResponse<Void> delete(String endpoint) {
        Response response = request()
                .when()
                .delete(endpoint)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();

        return new ApiResponse<>(response, Void.class);
    }

    protected <T> ApiResponse<T> put(
            String endpoint,
            Object requestBody,
            Class<T> responseClass
    ) {
        Response response = request()
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();

        return new ApiResponse<>(response, responseClass);
    }
}