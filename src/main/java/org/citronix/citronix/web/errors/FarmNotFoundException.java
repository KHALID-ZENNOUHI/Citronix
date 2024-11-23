package org.citronix.citronix.web.errors;

public class FarmNotFoundException extends RuntimeException {
    public FarmNotFoundException() {
        super("Farm not found");
    }
}
