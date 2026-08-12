package ru.mikhail.qasandbox.client;

public record ApiResponse<T>(
        int statusCode,
        T body
) {
}