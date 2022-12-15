package com.info.coin.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.coin.client.websocket.dto.WebSocketRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class ExchangeWebSocketHandler extends BinaryWebSocketHandler {

    private final ObjectMapper ObjectMapper;

    private final List<WebSocketRequest> webSocketRequests;

    public ExchangeWebSocketHandler(List<WebSocketRequest> webSocketRequests) {
        this.webSocketRequests = webSocketRequests;
        this.ObjectMapper = new ObjectMapper();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage(ObjectMapper.writeValueAsString(webSocketRequests)));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        log.info("binary message : id={}", session.getId());
        log.info("message={}", StandardCharsets.UTF_8.decode(message.getPayload()));
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("ping poing message : id={}, message={}", session.getId(), message.getPayload());
    }
}
