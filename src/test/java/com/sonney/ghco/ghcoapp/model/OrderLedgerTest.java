package com.sonney.ghco.ghcoapp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderLedgerTest {

    @Test
    public void orderLedgerCreatesAllObjectsToPlaceATrade() {
        OrderLedger orderLedger = new OrderLedger();
        Trade fakeTrade = new Trade("1", "testCode", "GBP",
                Side.B, 10.0, 2, "testPortfolio", Action.NEW,
                "testAccount", "testUser", LocalDateTime.now()
        );

        orderLedger.placeTrade(fakeTrade);

        Map<String, Double> currencyValuations = orderLedger.getAllTotalCurrencyValuations();
        assertEquals(currencyValuations.get("GBP"), 20);
        CurrencyValuation currencyValuation = orderLedger.getTotalCurrencyValuation("GBP");
        assertEquals(currencyValuation.getValue(), 20.0);
        assertEquals(currencyValuation.getCurrency(), "GBP");

        //trade to sell the stock
        Trade fakeTrade2 = new Trade("2", "testCode", "GBP",
                Side.S, 10.0, 2, "testPortfolio", Action.NEW,
                "testAccount", "testUser", LocalDateTime.now()
        );

        orderLedger.placeTrade(fakeTrade2);
        Map<String, Double> currencyValuations2 = orderLedger.getAllTotalCurrencyValuations();
        assertEquals(0.0, currencyValuations2.get("GBP"));
        
    }

}