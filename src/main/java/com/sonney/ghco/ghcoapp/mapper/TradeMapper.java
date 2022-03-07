package com.sonney.ghco.ghcoapp.mapper;

import com.sonney.ghco.ghcoapp.model.Action;
import com.sonney.ghco.ghcoapp.model.Side;
import com.sonney.ghco.ghcoapp.model.Trade;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TradeMapper {

    public Trade createTrade(String[] lineReaderValues) {
        String tradeId = lineReaderValues[0];
        String bbgCode = lineReaderValues[1];
        String currency = lineReaderValues[2];
        Side side = Side.valueOf(lineReaderValues[3]);
        double price = Double.parseDouble(lineReaderValues[4]);
        long volume = Long.parseLong(lineReaderValues[5]);
        String portfolio = lineReaderValues[6];
        Action action = Action.valueOf(lineReaderValues[7]);
        String account = lineReaderValues[8];
        String strategy = lineReaderValues[9];
        String user = lineReaderValues[10];
        LocalDateTime tradeTimeUTC = LocalDateTime.parse(lineReaderValues[11]);

        return new Trade(tradeId, bbgCode, currency, side, price, volume, portfolio, action, account, user, tradeTimeUTC);

    }
}
