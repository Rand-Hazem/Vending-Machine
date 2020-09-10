/**
 * Vending Machine throw this exception if it dosn't have sufficient change to complete the operation
 */
package com.machine.exceptions;

public class NotSufficientChangeException extends RuntimeException {

    private String message;

    public NotSufficientChangeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
