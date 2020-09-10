/**
 * Vending Machine throws this exception when the user didn't pay the full price for the collected item
 */
package com.machine.exceptions;

public class NotFullPaidException extends RuntimeException {
    private String message;

    public NotFullPaidException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
