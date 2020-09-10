/**
 * Vending Machine throw this exception if selected item is wrong
 */
package com.machine.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private String message;

    public ProductNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


}
