package com.invest.coin.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class UpbitConnectorTest {

    @Autowired
    UpbitConnector upbitConnector;

    @Test
    public void getAllCointAllMarketTest() {
        upbitConnector.getAllCointAllMarket();
    }

}