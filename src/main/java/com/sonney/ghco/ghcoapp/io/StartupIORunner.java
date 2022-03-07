package com.sonney.ghco.ghcoapp.io;

import com.sonney.ghco.ghcoapp.mapper.TradeMapper;
import com.sonney.ghco.ghcoapp.model.Trade;
import com.sonney.ghco.ghcoapp.service.OrderLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * Pick one. Command line runner or this?
 */
@Component
public class StartupIORunner {

    private OrderLedgerService orderLedgerService;
    private TradeMapper tradeMapper;

    @Autowired
    public StartupIORunner(OrderLedgerService orderLedgerService, TradeMapper mapper) {
        this.orderLedgerService = orderLedgerService;
        this.tradeMapper = mapper;
    }

    @PostConstruct
    private void init() throws IOException {

        ClassPathResource resource = new ClassPathResource("trades.csv");
        InputStream inputStream = resource.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            br.readLine(); //ignore first line
            while((line = br.readLine())!= null) {
                String[] values = line.split(",");
                //make a Trade object with the values;
                Trade trade = tradeMapper.createTrade(values);
                //stuff it into ledger
                orderLedgerService.placeTrade(trade);
            }
        }
    }
}
