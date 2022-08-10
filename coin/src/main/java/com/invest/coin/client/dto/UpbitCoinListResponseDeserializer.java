package com.invest.coin.client.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public class UpbitCoinListResponseDeserializer extends JsonDeserializer<UpbitCoinListResponseDto> {
    @Override
    public UpbitCoinListResponseDto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return new UpbitCoinListResponseDto(p.readValueAs(new TypeReference<List<UpbitCoinListDto>>() {
        }));
    }
}
