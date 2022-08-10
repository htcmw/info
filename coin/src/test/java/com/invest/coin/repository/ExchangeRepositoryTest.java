package com.invest.coin.repository;

import com.invest.coin.constant.ExchangeType;
import com.invest.coin.entity.Exchange;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class ExchangeRepositoryTest {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @BeforeEach
    @DisplayName("테이터 준비")
    void setUp() {
        exchangeRepository.save(Exchange.builder().name(ExchangeType.UPBIT).build());
        exchangeRepository.save(Exchange.builder().name(ExchangeType.FTX).build());
        exchangeRepository.save(Exchange.builder().name(ExchangeType.BINANCE).build());
        exchangeRepository.save(Exchange.builder().name(ExchangeType.BITHUMB).build());
    }

    @Test
    @DisplayName("테이터 조회 테스트")
    public void getExchangesByNameTest() {
        Assertions.assertThat(exchangeRepository.getExchangesByName(ExchangeType.UPBIT).orElse(null)).isNotNull();
        Assertions.assertThat(exchangeRepository.getExchangesByName(ExchangeType.FTX).orElse(null)).isNotNull();
        Assertions.assertThat(exchangeRepository.getExchangesByName(ExchangeType.BINANCE).orElse(null)).isNotNull();
        Assertions.assertThat(exchangeRepository.getExchangesByName(ExchangeType.BITHUMB).orElse(null)).isNotNull();
    }

}