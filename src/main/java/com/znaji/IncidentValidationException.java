package com.znaji;

import java.util.List;

public class IncidentValidationException extends RuntimeException {
    private final List<String> errors;

    public IncidentValidationException(List<String> errors) {
        super("Incident validation failed");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}