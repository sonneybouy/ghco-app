package com.sonney.ghco.ghcoapp.model;

import java.util.HashMap;
import java.util.Map;

public class OrderLedger {

    private final Map<String, UserBook> userBooks = new HashMap<>();

    public void placeTrade(Trade trade) {
        if (userBooks.containsKey(trade.getUser())) {
            UserBook userBook = userBooks.get(trade.getUser());
            userBook.placeTrade(trade);
        } else {
            UserBook newUserBook = new UserBook();
            newUserBook.placeTrade(trade);
            userBooks.put(trade.getUser(), newUserBook);
        }
    }

    public CurrencyValuation getTotalCurrencyValuation(String currency) {
        CurrencyValuation finalValuation = new CurrencyValuation(currency, 0.0);
        userBooks.forEach((user, userbook) -> {
            CurrencyValuation currencyValuation = userbook.getTotalCurrencyValuation(currency);
            finalValuation.setValue(finalValuation.getValue() + currencyValuation.getValue());
        });

        return finalValuation;
    }

    public Map<String,Double> getAllTotalCurrencyValuations() {
        Map<String, Double> allValuations = new HashMap<>();
        userBooks.forEach((user, userbook) -> {
            Map<String,Double> userValuations = userbook.getAllCurrencyValuations();
            userValuations.forEach((currencyName, currencyValue) -> {
                allValuations.merge(currencyName, currencyValue, (a,b)->a+b);
            });
        });
        return allValuations;

    }

}
