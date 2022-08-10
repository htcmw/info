package com.invest.coin.entity;

import com.invest.coin.constant.CoinExchangeStatus;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "COIN_EXCHANGES", indexes = @Index(name = "index_coin_exchange", columnList = "COIN_IDX, EXCHANGE_IDX", unique = true))
public class CoinExchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDX")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COIN_IDX")
    private Coin coin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXCHANGE_IDX")
    private Exchange exchange;

    @OneToMany(mappedBy = "coinExchange", fetch = FetchType.LAZY)
    private Set<CryptoCurrency> currencies;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CoinExchangeStatus coinExchangeStatus = CoinExchangeStatus.NORMAL;

    public void addCurrency(CryptoCurrency cryptoCurrency) {
        if (cryptoCurrency == null) return;
        currencies.add(cryptoCurrency);
    }

    public void addCurrencies(Set<CryptoCurrency> currencies) {
        if (CollectionUtils.isEmpty(currencies)) return;
        for (CryptoCurrency cryptoCurrency : currencies) {
            addCurrency(cryptoCurrency);
        }
    }

    public void changeStatus(CoinExchangeStatus coinExchangeStatus) {
        this.coinExchangeStatus = coinExchangeStatus;
    }
}
