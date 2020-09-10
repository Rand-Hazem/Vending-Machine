/**
 * Represent User payment card
 * debt card, visa card ... etc
 */
package com.machine.component;

public class UserCard {

    private double balance;

    public UserCard(int balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
