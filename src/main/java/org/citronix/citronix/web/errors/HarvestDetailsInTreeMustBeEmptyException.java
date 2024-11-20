package org.citronix.citronix.web.errors;

public class HarvestDetailsInTreeMustBeEmptyException extends RuntimeException {
    public HarvestDetailsInTreeMustBeEmptyException() {
        super("Harvest details List in Tree must be empty");
    }
}
