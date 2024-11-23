package org.citronix.citronix.web.errors;

public class ThereIsNoTreeInFieldException extends RuntimeException {
    public ThereIsNoTreeInFieldException() {
        super("There is no tree in field.");
    }
}
