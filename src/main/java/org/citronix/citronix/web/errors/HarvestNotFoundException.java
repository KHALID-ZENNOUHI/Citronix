package org.citronix.citronix.web.errors;

public class HarvestNotFoundException extends RuntimeException {
    public HarvestNotFoundException() {
        super("Harvest not found");
    }
}
