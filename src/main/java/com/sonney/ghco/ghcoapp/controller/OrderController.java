package com.sonney.ghco.ghcoapp.controller;

import com.sonney.ghco.ghcoapp.model.CurrencyValuation;
import com.sonney.ghco.ghcoapp.model.Trade;
import com.sonney.ghco.ghcoapp.service.OrderLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value= "/api")
public class OrderController {

    private OrderLedgerService orderLedgerService;

    @Autowired
    public OrderController(OrderLedgerService orderLedgerService) {
        this.orderLedgerService = orderLedgerService;
    }


    @GetMapping("/totalValue/{currency}")
    public ResponseEntity<CurrencyValuation> getTotalValuation(@PathVariable String currency) {
        return ResponseEntity.ok(orderLedgerService.getTotalValuation(currency));
    }

    @GetMapping("/totalValue/allValuations")
    public ResponseEntity<Map<String, Double>> getAllTotalValuations() {
        return ResponseEntity.ok(orderLedgerService.getAllTotalCurrencyValuations());
    }

    @PostMapping("/placeTrade")
    public ResponseEntity<String> placeTrade(@RequestBody Trade trade) {
        orderLedgerService.placeTrade(trade);
        return ResponseEntity.ok("Success");
    }



}
