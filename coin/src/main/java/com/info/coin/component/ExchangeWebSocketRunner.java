package com.info.coin.component;


import com.info.coin.client.websocket.UpbitWebSocketClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeWebSocketRunner implements CommandLineRunner {

    private final UpbitWebSocketClient upbitWebSocketClient;

    @Override
    public void run(String... args) throws Exception {
        upbitWebSocketClient.connect();
    }
}