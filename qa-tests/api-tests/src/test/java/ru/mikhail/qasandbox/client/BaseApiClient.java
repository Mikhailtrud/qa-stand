package ru.mikhail.qasandbox.client;

import io.restassured.RestAssured;
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

    protected <T> T get(String endpoint,
                        int expectedStatusCode,
                        Class<T> responseClass) {

        return request()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

    protected <T> T post(String endpoint,
                         Object requestBody,
                         int expectedStatusCode,
                         Class<T> responseClass) {

        return request()
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

    protected void delete(String endpoint,
                          int expectedStatusCode) {

        request()
                .when()
                .delete(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode);
    }

    protected <T> T put(String endpoint,
                         Object requestBody,
                         int expectedStatusCode,
                         Class<T> responseClass) {

        return request()
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }
}