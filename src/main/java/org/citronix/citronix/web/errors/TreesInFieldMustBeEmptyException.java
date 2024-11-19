package org.citronix.citronix.web.errors;

public class TreesInFieldMustBeEmptyException extends RuntimeException {
    public TreesInFieldMustBeEmptyException() {
        super("The list of Trees in field must be empty");
    }
}
