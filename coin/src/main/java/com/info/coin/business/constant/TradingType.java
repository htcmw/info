package com.info.coin.business.constant;

import lombok.Getter;

@Getter
public enum TradingType {

    TICKER("ticker", "현재가"),
    TRADE("trade", "체결"),
    ORDERBOOK("orderbook", "호가");

    TradingType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private final String value;
    private final String desc;
}