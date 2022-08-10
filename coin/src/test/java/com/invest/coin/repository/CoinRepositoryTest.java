package com.invest.coin.repository;

import com.invest.coin.entity.Coin;
import com.invest.coin.entity.embeddable.CoinName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class CoinRepositoryTest {

    @Autowired
    private CoinRepository coinRepository;

    @BeforeEach
    @DisplayName("테이터 준비")
    void setUp() {
        coinRepository.save(Coin.builder()
                .name(CoinName.builder().symbol("BTC").korean("비트코인").english("Bitcoin").build())
                .build());
        coinRepository.save(Coin.builder()
                .name(CoinName.builder().symbol("ETH").korean("이더리움").english("Ethereum").build())
                .build());
    }

    @DisplayName("단위 테스트 후 테이터 삭제 ")
    @AfterEach
    public void cleanUp() {
        coinRepository.deleteAll();
    }

    @Test
    @DisplayName("findBySymbol(String symbol)")
    void findBySymbolTest() {
        Assertions.assertThat(coinRepository.findBySymbol(null).orElse(null)).isNull();
        Assertions.assertThat(coinRepository.findBySymbol("").orElse(null)).isNull();
        Assertions.assertThat(coinRepository.findBySymbol("Wrong").orElse(null)).isNull();
        Assertions.assertThat(coinRepository.findBySymbol("BTC").orElse(null)).isNotNull();
    }
}