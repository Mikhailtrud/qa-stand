package ru.mikhail.qasandbox.client;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public abstract class BaseApiClient {

    protected <T> T get(String endpoint,
                        int expectedStatusCode,
                        Class<T> responseClass) {

        return RestAssured.given()
                .filter(new AllureRestAssured())
                .spec(ru.mikhail.qasandbox.specifications.Specifications.requestSpec())
                .when()
                .get(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

    protected <T> T post(String endpoint,
                         Object requestBody,
                         int expectedStatusCode,
                         Class<T> responseClass) {

        return RestAssured.given()
                .filter(new AllureRestAssured())
                .spec(ru.mikhail.qasandbox.specifications.Specifications.requestSpec())
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .as(responseClass);
    }

    protected void delete(String endpoint,
                          int expectedStatusCode) {

        RestAssured.given()
                .filter(new AllureRestAssured())
                .spec(ru.mikhail.qasandbox.specifications.Specifications.requestSpec())
                .when()
                .delete(endpoint)
                .then()
                .statusCode(expectedStatusCode);
    }
}