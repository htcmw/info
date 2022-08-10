package com.invest.coin.entity;

import com.invest.coin.constant.CoinExchangeStatus;
import com.invest.coin.constant.ExchangeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EXCHANGE")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ExchangeType name;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CoinExchangeStatus coinExchangeStatus = CoinExchangeStatus.NORMAL;

    public String getNameString() {
        return name.name();
    }
}
