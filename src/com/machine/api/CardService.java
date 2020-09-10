package com.machine.api;

import com.machine.component.UserCard;

public interface CardService {

    boolean deductAmount(UserCard card, long amount);
}
