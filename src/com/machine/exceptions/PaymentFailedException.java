/**
 * Vending Machine throw this exception if payment from card failed
 */
package com.machine.exceptions;

public class PaymentFailedException extends RuntimeException {
    private String message;

    public PaymentFailedException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
