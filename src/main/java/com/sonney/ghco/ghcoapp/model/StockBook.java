package com.sonney.ghco.ghcoapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class StockBook {

    Map<String, Trade> tradeMap =  new HashMap<>();

    public void placeTrade(Trade trade) {
        if ( Action.NEW.equals(trade.getAction()) || Action.AMEND.equals(trade.getAction())) {
            tradeMap.put(trade.getTradeId(), trade);
        } else  {
            tradeMap.remove(trade.getTradeId());
        }
    }

    public Long getTotalVolume() {
        //consider buy and sell at this poin
        AtomicReference<Long> volume = new AtomicReference<>(0L);
        tradeMap.forEach((tradeId, trade) -> {
            if (Side.B.equals(trade.getSide())) {
                volume.updateAndGet(v -> v + trade.getVolume());
            } else {
                volume.updateAndGet(v -> v - trade.getVolume());
            }
        });
        return volume.get();
    }

    public Double getTotalValue() {
        final Double[] value = {0.0};
        tradeMap.forEach((tradeId, trade) -> {
           if(Side.B.equals(trade.getSide())) {
               value[0] += trade.getPrice() * trade.getVolume();
           } else {
               value[0] -= trade.getPrice() * trade.getVolume();
           }
        });
        return value[0];
    }

    //side, price, volume, action tradeTimeUTC
}
