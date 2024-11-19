package org.citronix.citronix.web.errors;

public class FieldsInFarmMustNotBeEmptyException extends RuntimeException {
    public FieldsInFarmMustNotBeEmptyException() {
        super("The list of Fields in farm must not be empty");
    }
}
