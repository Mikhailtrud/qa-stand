package utils;

import static io.restassured.RestAssured.given;

import config.EnvironmentConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public final class BackendApiClient {
    private final RequestSpecification requestSpec;

    public BackendApiClient() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(EnvironmentConfig.backendUrl())
                .setContentType(ContentType.JSON)
                .build();
    }

    public String login(String email, String password) {
        return given()
                .spec(requestSpec)
                .body(Map.of("email", email, "password", password))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    public long createUser(String token, String name, String email, String password, String role) {
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(Map.of(
                        "name", name,
                        "email", email,
                        "password", password,
                        "role", role
                ))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getLong("id");
    }

    public void deleteUser(String token, long userId) {
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/users/{id}", userId)
                .then()
                .statusCode(200);
    }
}
