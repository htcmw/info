package com.info.coin.client.websocket.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.info.coin.business.constant.TradingType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@JsonSerialize(using = UpbitWebSocketRequestDtoSerializer.class)
public class UpbitWebSocketRequestDto implements WebSocketRequest {

    private final String ticket = UUID.randomUUID().toString();

    private final TradingType type;

    private final List<String> codes;

    private final boolean isOnlySnapshot;

    private final boolean isOnlyRealtime;

    @Builder
    public UpbitWebSocketRequestDto(TradingType type, List<String> codes, boolean isOnlySnapshot, boolean isOnlyRealtime) {
        this.type = type;
        this.codes = codes;
        this.isOnlySnapshot = isOnlySnapshot;
        this.isOnlyRealtime = isOnlyRealtime;
    }

}
