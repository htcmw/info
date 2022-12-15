package com.info.coin.client.websocket.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UpbitWebSocketRequestDtoSerializer extends StdSerializer<UpbitWebSocketRequestDto> {

    public UpbitWebSocketRequestDtoSerializer() {
        this(null);
    }

    protected UpbitWebSocketRequestDtoSerializer(Class<UpbitWebSocketRequestDto> t) {
        super(t);
    }

    @Override
    public void serialize(UpbitWebSocketRequestDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("ticket", value.getTicket());
        gen.writeEndObject();
        gen.writeStartObject();
        gen.writeObjectField("type", value.getType().getValue());
        gen.writeObjectField("codes", value.getCodes());
        gen.writeEndObject();
    }
}
