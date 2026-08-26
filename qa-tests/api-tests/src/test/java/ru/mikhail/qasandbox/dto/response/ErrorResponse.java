package ru.mikhail.qasandbox.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ErrorResponse(
        String timestamp,
        Integer status,
        String error,
        String message,
        String path,
        Map<String, String> errors
) {
}
