package com.machine.factory;

import com.machine.api.VendingMachine;
import com.machine.VendingMachineImpl;

public class VendingMachineFactory {
    public static VendingMachine getVendingMachine() {
        return new VendingMachineImpl();
    }

}
