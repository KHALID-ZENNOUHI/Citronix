package org.citronix.citronix.web.errors;

public class TreeNotFoundException extends RuntimeException {
    public TreeNotFoundException() {
        super("Tree not found.");
    }
}
