package org.citronix.citronix.web.errors;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException() {
        super("Sale not found");
    }
}
