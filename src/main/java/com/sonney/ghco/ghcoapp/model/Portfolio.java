package com.sonney.ghco.ghcoapp.model;

import java.util.*;

public class Portfolio {

    //currency to stockcodebook
    private Map<String, BBGCodeBook> currencyBooks = new HashMap<>();

    public void placeTrade(Trade trade) {
        if ( currencyBooks.containsKey(trade.getCurrency())) {
            BBGCodeBook bbgCodeBook = currencyBooks.get(trade.getCurrency());
            bbgCodeBook.placeTrade(trade);

        } else {
            BBGCodeBook bbgCodeBook = new BBGCodeBook();
            bbgCodeBook.placeTrade(trade);
            currencyBooks.put(trade.getCurrency(), bbgCodeBook);
        }
    }

    public CurrencyValuation getValuationForCurrency(String currency) {
        BBGCodeBook bbgCodeBook = currencyBooks.getOrDefault(currency, null);
        //handle null

        Double value = bbgCodeBook.getTotalValue();
        return new CurrencyValuation(currency, value);

    }

    public List<CurrencyValuation> getTotalValuation() {
        List<CurrencyValuation> currencyValuations = new ArrayList<>();
        currencyBooks.forEach((currency, bbgCodeBook) -> {
           Double value = bbgCodeBook.getTotalValue();
           CurrencyValuation currencyValuation = new CurrencyValuation(currency, value);
           currencyValuations.add(currencyValuation);
        });

        return currencyValuations;
    }

}
