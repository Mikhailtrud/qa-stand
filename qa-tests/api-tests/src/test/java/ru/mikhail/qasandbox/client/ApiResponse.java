package ru.mikhail.qasandbox.client;

import io.restassured.response.Response;
import ru.mikhail.qasandbox.dto.response.ErrorResponse;

public class ApiResponse<T> {

    private final Response response;
    private final Class<T> responseClass;

    public ApiResponse(Response response, Class<T> responseClass) {
        this.response = response;
        this.responseClass = responseClass;
    }

    public int statusCode() {
        return response.statusCode();
    }

    public T body() {
        return response.as(responseClass);
    }

    public ErrorResponse errorBody() {
        return response.as(ErrorResponse.class);
    }
}