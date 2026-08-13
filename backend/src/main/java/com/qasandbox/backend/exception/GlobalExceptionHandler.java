package com.qasandbox.backend.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.qasandbox.backend.dto.error.ErrorResponse;
import com.qasandbox.backend.dto.error.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(
            ApiException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                exception.getStatus().value(),
                exception.getStatus().getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(exception.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {

        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ValidationErrorResponse response = new ValidationErrorResponse(
                LocalDateTime.now(),
                422,
                "Unprocessable Entity",
                "Validation failed",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity
                .unprocessableEntity()
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ValidationErrorResponse> handleUnreadableMessage(
            HttpMessageNotReadableException exception,
            HttpServletRequest request
    ) {

        Map<String, String> errors = new LinkedHashMap<>();
        Throwable cause = exception.getCause();

        while (cause != null && !(cause instanceof JsonMappingException)) {
            cause = cause.getCause();
        }

        if (cause instanceof JsonMappingException mappingException
                && !mappingException.getPath().isEmpty()) {
            errors.put(
                    mappingException.getPath().getFirst().getFieldName(),
                    "Invalid value"
            );
        }

        ValidationErrorResponse response = new ValidationErrorResponse(
                LocalDateTime.now(),
                422,
                "Unprocessable Entity",
                "Validation failed",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity
                .unprocessableEntity()
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                400,
                "Bad Request",
                "Invalid value for parameter '" + exception.getName() + "'",
                request.getRequestURI()
        );

        return ResponseEntity
                .badRequest()
                .body(response);
    }

}
