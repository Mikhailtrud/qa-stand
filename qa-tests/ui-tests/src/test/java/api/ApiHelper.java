package api;

import data.testData.UserData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

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

    public OptionalLong findUserId(String token, String email) {
        List<Map<String, Object>> users = given()
                .baseUri(baseUrl)
                .auth().oauth2(token)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("$");

        return users.stream()
                .filter(user -> email.equals(user.get("email")))
                .map(user -> (Number) user.get("id"))
                .mapToLong(Number::longValue)
                .findFirst();
    }

    public void deleteUserIfExists(String token, long userId) {
        Response response = given()
                .baseUri(baseUrl)
                .auth().oauth2(token)
                .when()
                .delete("/users/{id}", userId);

        int statusCode = response.statusCode();
        if (statusCode != 200 && statusCode != 404) {
            throw new AssertionError(
                    "Failed to clean up user %d: HTTP %d, body: %s"
                            .formatted(userId, statusCode, response.asString())
            );
        }
    }

    private static String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
