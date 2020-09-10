/**
 * Inventory used to mantain Products, Coins and Notes
 */
package com.machine.component;

import java.util.HashMap;

public class Inventory<T1, T2> {

    private HashMap<T1, T2> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public HashMap<T1, T2> getInventory() {
        return inventory;
    }

    public T2 putItem(T1 t1, T2 t2) {
        return inventory.put(t1, t2);
    }

    public T2 getItem(T1 key) {
        return inventory.getOrDefault(key, null);
    }
}
