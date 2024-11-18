package org.citronix.citronix.web.errors;

public class IdMustBeNotNullException extends RuntimeException {
    public IdMustBeNotNullException() {
        super("Id must be not null");
    }
}
