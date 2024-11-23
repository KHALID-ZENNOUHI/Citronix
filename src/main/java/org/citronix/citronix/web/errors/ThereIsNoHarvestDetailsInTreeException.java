package org.citronix.citronix.web.errors;

public class ThereIsNoHarvestDetailsInTreeException extends RuntimeException {
    public ThereIsNoHarvestDetailsInTreeException() {
        super("There is no harvest details in tree.");
    }
}
