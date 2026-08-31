package api;

import static io.restassured.RestAssured.given;

import config.EnvironmentConfig;
import io.restassured.http.ContentType;
import java.util.Map;

public final class ApiAuthClient {
    public String loginAsAdmin() {
        return login(EnvironmentConfig.adminEmail(), EnvironmentConfig.adminPassword());
    }

    public String login(String email, String password) {
        return given()
                .baseUri(EnvironmentConfig.backendUrl())
                .contentType(ContentType.JSON)
                .body(Map.of("email", email, "password", password))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}
