package com.info.coin.client.websocket;

import com.info.coin.business.constant.TradingType;
import com.info.coin.business.service.UpbitCoinService;
import com.info.coin.client.websocket.dto.UpbitWebSocketRequestDto;
import com.info.coin.client.websocket.dto.WebSocketRequest;
import com.info.coin.handler.ExchangeWebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpbitWebSocketClient implements ExchangeWebSocketClient {
    private final UpbitCoinService upbitCoinService;

    @Override
    public void connect() {
        List<String> markets = upbitCoinService.getMarkets();
        List<WebSocketRequest> upbitWebSocketRequests = new ArrayList<>();
        upbitWebSocketRequests.add(new UpbitWebSocketRequestDto(TradingType.TICKER, markets, false, true));
        upbitWebSocketRequests.add(new UpbitWebSocketRequestDto(TradingType.TRADE, markets, false, true));
        upbitWebSocketRequests.add(new UpbitWebSocketRequestDto(TradingType.ORDERBOOK, markets, false, true));

        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                new StandardWebSocketClient(),
                new ExchangeWebSocketHandler(upbitWebSocketRequests),
                "wss://api.upbit.com/websocket/v1"
        );
        manager.setAutoStartup(true);
        manager.startInternal();
    }
}
