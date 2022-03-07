package com.sonney.ghco.ghcoapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {

    private final Map<String, Portfolio> portfolioMap = new HashMap<>();

    public void placeTrade(Trade trade) {
        if (portfolioMap.containsKey(trade.getPortfolio())) {
            Portfolio portfolio = portfolioMap.get(trade.getPortfolio());
            portfolio.placeTrade(trade);
        } else {
            Portfolio newPortfolio = new Portfolio();
            newPortfolio.placeTrade(trade);
            portfolioMap.put(trade.getPortfolio(), newPortfolio);
        }
    }

    public CurrencyValuation getTotalValuationForCurrency(String currency) {
        CurrencyValuation finalValuation = new CurrencyValuation(currency, 0.0);
        portfolioMap.forEach((portfolioName, portfolio) -> {
            CurrencyValuation currencyValuation = portfolio.getValuationForCurrency(currency);
            finalValuation.setValue(finalValuation.getValue() + currencyValuation.getValue());
        });
        return finalValuation;
    }

    public Map<String, Double> getTotalValuationForAllCurrencies() {
        Map<String, Double> currencyValuationsMap = new HashMap<>();
        portfolioMap.forEach((porfolioName, portfolio) -> {
            List<CurrencyValuation> currencyValuations = portfolio.getTotalValuation();
            currencyValuations.forEach(cv -> {
                if ( currencyValuationsMap.containsKey(cv.getCurrency())) {
                    currencyValuationsMap.put(cv.getCurrency(), currencyValuationsMap.get(cv.getCurrency()) + cv.getValue());
                } else {
                    currencyValuationsMap.put(cv.getCurrency(), cv.getValue());
                }
            });
        });

        return currencyValuationsMap;

    }

}
