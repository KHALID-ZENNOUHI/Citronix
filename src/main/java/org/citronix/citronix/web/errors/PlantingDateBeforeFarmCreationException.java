package org.citronix.citronix.web.errors;

public class PlantingDateBeforeFarmCreationException extends RuntimeException {
    public PlantingDateBeforeFarmCreationException() {
        super("Planting date is before farm creation date");
    }
}
