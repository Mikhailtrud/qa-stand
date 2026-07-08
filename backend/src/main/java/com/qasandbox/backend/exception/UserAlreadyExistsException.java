package com.qasandbox.backend.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiException {

    public UserAlreadyExistsException(String email) {
        super(
                HttpStatus.CONFLICT,
                "User with email '" + email + "' already exists."
        );
    }

}