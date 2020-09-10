package com.machine.api;

import com.machine.component.UserCard;

public interface VendingMachine {

    long selectItem(int key);

    void insertMoney(Money money);

    void insertCard(UserCard userCard);

    void reset();
}
