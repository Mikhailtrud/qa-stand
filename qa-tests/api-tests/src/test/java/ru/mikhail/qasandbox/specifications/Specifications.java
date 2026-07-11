package ru.mikhail.qasandbox.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.mikhail.qasandbox.config.Config;

public final class Specifications {

    private Specifications() {
    }

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }
}