package ru.mikhail.qasandbox.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.mikhail.qasandbox.config.ApiConfig;

public final class Specifications {

    private Specifications() {
    }

    public static RequestSpecification requestSpec() {

        return requestSpec(ApiConfig.getBaseUrl());
    }

    public static RequestSpecification requestSpec(String baseUri) {

        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.METHOD)
                .addFilter(new AllureRestAssured())
                .build();
    }
}
