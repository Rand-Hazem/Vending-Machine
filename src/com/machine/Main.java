package com.machine;

import com.machine.api.VendingMachine;
import com.machine.component.UserCard;
import com.machine.enums.Coin;
import com.machine.factory.VendingMachineFactory;

public class Main {

    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachineFactory.getVendingMachine();
        vendingMachine.selectItem(2);
        vendingMachine.insertMoney(Coin.FIFTY_CENT);
        vendingMachine.insertMoney(Coin.ONE_DOLLAR);
        vendingMachine.selectItem(6);
        vendingMachine.insertCard(new UserCard(100 * 5000));


    }
}
