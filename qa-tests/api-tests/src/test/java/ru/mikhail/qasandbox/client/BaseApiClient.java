package ru.mikhail.qasandbox.client;

import io.restassured.RestAssured;

public abstract class BaseApiClient {

    protected <T> T get(String endpoint,
                        int expectedStatusCode,
                        Class<T> responseClass) {

        return RestAssured.given()
                .spec(ru.mikhail.qasandbox.specifications.Specifications.requestSpec())
                .when()
                .get(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

    protected <T> T get(String endpoint,
                        String token,
                        int expectedStatusCode,
                        Class<T> responseClass) {

        return RestAssured.given()
                .spec(ru.mikhail.qasandbox.specifications.Specifications.requestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

    protected <T> T post(String endpoint,
                         Object requestBody,
                         int expectedStatusCode,
                         Class<T> responseClass) {

        return RestAssured.given()
                .spec(ru.mikhail.qasandbox.specifications.Specifications.requestSpec())
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

        RestAssured.given()
                .spec(ru.mikhail.qasandbox.specifications.Specifications.requestSpec())
                .when()
                .delete(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode);
    }
}