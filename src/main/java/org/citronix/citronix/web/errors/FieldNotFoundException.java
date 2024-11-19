package org.citronix.citronix.web.errors;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException() {
        super("Field not found");
    }
}
