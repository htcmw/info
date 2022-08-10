package com.invest.coin.client;

import com.invest.coin.client.dto.UpbitCoinListResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class UpbitFeignClientTest {

    @Autowired
    private UpbitFeignClient upbitFeignClient;

    @Test
    @DisplayName("업비트 코인 리스트 데이터 불러오기")
    void getAllCointAllMarket() {
        UpbitCoinListResponseDto coinListResponse = upbitFeignClient.getAllCointAllMarket(true);
        Assertions.assertThat(coinListResponse.getUpbitCoinList()).isNotEmpty();
    }
}