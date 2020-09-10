/**
 * Enum represent supported banknote by vending machine
 */
package com.machine.enums;

import com.machine.api.Money;

public enum Note implements Money {
    TWENTY_DOLLAR(2000), FIFTY_DOLLAR(5000);

    private int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Note getNote(int val) {
        switch (val) {
            case 2000:
                return TWENTY_DOLLAR;
            case 5000:
                return FIFTY_DOLLAR;
        }
        return null;
    }
}
