package org.citronix.citronix.web.errors;

public class MaxAllowedTreesDepassedException extends RuntimeException {
    public MaxAllowedTreesDepassedException() {
        super("Field exceeds maximum tree density.");
    }
}
