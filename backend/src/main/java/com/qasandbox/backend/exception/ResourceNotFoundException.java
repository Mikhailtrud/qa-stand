package com.qasandbox.backend.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String resourceName, Object id) {
        super(
                HttpStatus.NOT_FOUND,
                resourceName + " with id '" + id + "' not found."
        );
    }

}