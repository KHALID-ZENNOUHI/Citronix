package org.citronix.citronix.web.errors;

public class IdMustBeNullException extends RuntimeException {
    public IdMustBeNullException() {
        super("Id must be null");
    }
}
