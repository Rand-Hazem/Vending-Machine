package com.machine.factory;

import com.machine.api.CardService;
import com.machine.service.CardServiceImpl;

public class CardServiceFactory {
    public static CardService getCardService() {
        return new CardServiceImpl();
    }
}
