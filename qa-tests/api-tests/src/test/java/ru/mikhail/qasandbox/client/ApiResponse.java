package ru.mikhail.qasandbox.client;

import io.restassured.response.Response;
import ru.mikhail.qasandbox.dto.response.ErrorResponse;

public class ApiResponse<T> {

    private final Response response;
    private final Class<T> responseClass;
    private T body;
    private ErrorResponse errorBody;
    private boolean bodyDeserialized;
    private boolean errorBodyDeserialized;

    public ApiResponse(Response response, Class<T> responseClass) {
        this.response = response;
        this.responseClass = responseClass;
    }

    public int statusCode() {
        return response.statusCode();
    }

    public T body() {
        if (!bodyDeserialized) {
            body = response.as(responseClass);
            bodyDeserialized = true;
        }

        return body;
    }

    public ErrorResponse errorBody() {
        if (!errorBodyDeserialized) {
            errorBody = response.as(ErrorResponse.class);
            errorBodyDeserialized = true;
        }

        return errorBody;
    }
}
