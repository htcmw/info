package com.info.coin.business.dto;

import java.util.List;

public record MarketResponseDto(List<String> markets) {
    public static MarketResponseDto of(List<String> markets) {
        return new MarketResponseDto(markets);
    }

}
