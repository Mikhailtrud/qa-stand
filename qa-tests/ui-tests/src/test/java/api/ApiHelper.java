package api;

import data.testData.UserData;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private final String baseUrl;

    public ApiHelper() {
        this.baseUrl = ApiTestConfig.baseUrl();
    }

    public String login() {
        return login(ApiTestConfig.adminEmail(), ApiTestConfig.adminPassword());
    }

    public String login(String email, String password) {
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body("""
                        {"email":"%s","password":"%s"}
                        """.formatted(escape(email), escape(password)))
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    public long createUser(String token, UserData user) {
        return given()
                .baseUri(baseUrl)
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body("""
                        {"name":"%s","email":"%s","password":"%s","role":"%s"}
                        """.formatted(
                        escape(user.name()),
                        escape(user.email()),
                        escape(user.password()),
                        escape(user.role())
                ))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getLong("id");
    }

    public long getUser(String token, String email) {
        Number userId = given()
                .baseUri(baseUrl)
                .auth().oauth2(token)
                .when()
                .get("/users")
                .then().log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("find { it.email == '" + email + "' }.id");

        return userId == null ? 0 : userId.longValue();
    }

    public void deleteUser(String token, long userId) {
        given()
                .baseUri(baseUrl)
                .auth().oauth2(token)
                .when()
                .delete("/users/{id}", userId)
                .then()
                .statusCode(200);
    }

    private static String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
