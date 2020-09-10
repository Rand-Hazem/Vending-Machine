/**
 * Enum represent supported coin by vending machine
 */
package com.machine.enums;

import com.machine.api.Money;

public enum Coin implements Money {
    TEN_CENTS(10), TWENTY_CENTS(20), FIFTY_CENT(50), ONE_DOLLAR(100);

    private int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Coin getCoin(int val) {
        switch (val) {
            case 10:
                return TEN_CENTS;
            case 20:
                return TWENTY_CENTS;
            case 50:
                return FIFTY_CENT;
            case 100:
                return ONE_DOLLAR;
        }
        return null;
    }


}
