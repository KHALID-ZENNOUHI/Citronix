package org.citronix.citronix.web.errors;

public class PlantingSeasonIsOverException extends RuntimeException {
    public PlantingSeasonIsOverException() {
        super("Planting season is over.");
    }
}
