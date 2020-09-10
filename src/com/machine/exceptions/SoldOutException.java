/**
 * Vending Machine throw this exception if selected item is sold out
 */
package com.machine.exceptions;

public class SoldOutException extends RuntimeException {
    private String message;

    public SoldOutException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
