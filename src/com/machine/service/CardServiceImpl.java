/**
 * This class handel all operation related to user card
 */
package com.machine.service;

import com.machine.api.CardService;
import com.machine.component.UserCard;
import com.machine.exceptions.PaymentFailedException;

public class CardServiceImpl implements CardService {

    @Override
    public boolean deductAmount(UserCard card, long amount) {
        double newBalance = card.getBalance() - amount / 100.0;
        if (newBalance < 0) {
            throw new PaymentFailedException("No enough moeny in the card");
        }
        card.setBalance(newBalance);
        return true;
    }
}
