package com.sonney.ghco.ghcoapp.model;

import java.util.HashMap;
import java.util.Map;

public class BBGCodeBook {

    private Map<String, StockBook> stockBooks = new HashMap<>();

    public void placeTrade(Trade trade) {
        if (stockBooks.containsKey(trade.getBBGCode())) {
            StockBook stockBook = stockBooks.get(trade.getBBGCode());
            stockBook.placeTrade(trade);
        } else {
            StockBook stockBook = new StockBook();
            stockBook.placeTrade(trade);
            stockBooks.put(trade.getBBGCode(), stockBook);
        }
    }

    public Double getTotalValue() {
        final Double[] value = {0.0};
        stockBooks.forEach((bbgCode, stockBook) -> {
            value[0] += stockBook.getTotalValue();
        });

        return value[0];
    }

}
