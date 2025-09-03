package com.stefan.patientservice.exception;

public class InvalidPatientIdException extends RuntimeException {
    public InvalidPatientIdException(String id) {
        super("Patient id " + id + " is invalid");
    }
}
