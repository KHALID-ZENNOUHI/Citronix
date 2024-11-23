package org.citronix.citronix.web.errors;

public class FieldsInFarmMustBeEmptyException extends RuntimeException {
    public FieldsInFarmMustBeEmptyException() {
        super("The list of Fields in farm must be empty");
    }
}
