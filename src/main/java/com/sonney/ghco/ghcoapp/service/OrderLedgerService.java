package com.sonney.ghco.ghcoapp.service;

import com.sonney.ghco.ghcoapp.model.CurrencyValuation;
import com.sonney.ghco.ghcoapp.model.OrderLedger;
import com.sonney.ghco.ghcoapp.model.Trade;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderLedgerService {

    private OrderLedger orderLedger = new OrderLedger();

    public void placeTrade(Trade trade) {
        orderLedger.placeTrade(trade);
    }

    public CurrencyValuation getTotalValuation(String currency) {
        return orderLedger.getTotalCurrencyValuation(currency);
    }

    public Map<String, Double> getAllTotalCurrencyValuations() {
        return orderLedger.getAllTotalCurrencyValuations();
    }
}
