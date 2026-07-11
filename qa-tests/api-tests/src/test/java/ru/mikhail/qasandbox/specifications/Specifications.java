package ru.mikhail.qasandbox.specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.mikhail.qasandbox.config.Config;

public final class Specifications {

    private Specifications() {
    }

    public static RequestSpecification requestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(Config.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();
    }
}