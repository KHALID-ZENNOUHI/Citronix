package org.citronix.citronix.web.errors;

public class HarvestAlreadyExistsForThisSeasonException extends RuntimeException {
    public HarvestAlreadyExistsForThisSeasonException() {
        super("Harvest already exists for this season and year");
    }
}
