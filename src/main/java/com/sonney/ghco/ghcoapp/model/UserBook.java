package com.sonney.ghco.ghcoapp.model;

import java.util.HashMap;
import java.util.Map;

public class UserBook {
    private final Map<String, Account> accountBook = new HashMap<>();

    public void placeTrade(Trade trade) {
        if (accountBook.containsKey(trade.getAccount())) {
            Account account = accountBook.get(trade.getAccount());
            account.placeTrade(trade);
        } else {
            Account newAccount = new Account();
            newAccount.placeTrade(trade);
            accountBook.put(trade.getAccount(), newAccount);
        }
    }

    public CurrencyValuation getTotalCurrencyValuation(String currency) {
        CurrencyValuation finalValuation = new CurrencyValuation(currency, 0.0);
        accountBook.forEach((accountName, account) -> {
            CurrencyValuation valuation = account.getTotalValuationForCurrency(currency);
            finalValuation.setValue(finalValuation.getValue() +  valuation.getValue());
        });

        return finalValuation;
    }

    public Map<String, Double> getAllCurrencyValuations() {
        Map<String, Double> allValuations = new HashMap<>();
        accountBook.forEach((accountName, account) -> {
            Map<String, Double> valuationMap = account.getTotalValuationForAllCurrencies();
            valuationMap.forEach((newCurrency, newValue) ->{
                allValuations.merge(newCurrency, newValue, (a,b)-> a + b);
            });
        });

        return allValuations;
    }

}
