package com.sonney.ghco.ghcoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Trade {

    private String tradeId;
    private String BBGCode;
    private String currency;
    private Side side;
    private double price;
    private long volume;
    private String portfolio;
    private Action action;
    private String account;
    private String user;
    private LocalDateTime tradeTimeUTC;

}