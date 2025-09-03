package com.stefan.patientservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Patient with email " + email + " already exists");
    }
}
