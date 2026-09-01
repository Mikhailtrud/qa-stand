package framework.api;

import static io.restassured.RestAssured.given;

import framework.config.EnvironmentConfig;
import data.testData.UserData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

public final class UsersApiClient {
    private final ApiAuthClient authClient = new ApiAuthClient();

    public long createUser(UserData user) {
        String token = authClient.loginAsAdmin();
        return given()
                .baseUri(EnvironmentConfig.backendUrl())
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "name", user.name(),
                        "email", user.email(),
                        "password", user.password(),
                        "role", user.role()
                ))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getLong("id");
    }

    public void deleteByEmailIfExists(String email) {
        String token = authClient.loginAsAdmin();
        findUserIdByEmail(getUsers(token), email)
                .ifPresent(userId -> deleteUser(token, userId));
    }

    private List<Map<String, Object>> getUsers(String token) {
        return given()
                .baseUri(EnvironmentConfig.backendUrl())
                .auth().oauth2(token)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("$");
    }

    public void deleteByID(Long id) {
        String token = authClient.loginAsAdmin();
        deleteUser(token, id);
    }

    private OptionalLong findUserIdByEmail(List<Map<String, Object>> users, String email) {
        return users.stream()
                .filter(user -> email.equals(user.get("email")))
                .map(user -> (Number) user.get("id"))
                .mapToLong(Number::longValue)
                .findFirst();
    }

    private void deleteUser(String token, long userId) {
        Response response = given()
                .baseUri(EnvironmentConfig.backendUrl())
                .auth().oauth2(token)
                .when()
                .delete("/users/{id}", userId);

        int statusCode = response.statusCode();
        if (statusCode != 200 && statusCode != 204 && statusCode != 404) {
            throw new IllegalStateException(
                    "Could not clean up user %d: HTTP %d, body: %s"
                            .formatted(userId, statusCode, response.asString())
            );
        }
    }
}
