package com.info.coin.business.domain;

import com.info.coin.business.constant.CoinExchangeStatus;
import com.info.coin.business.constant.ExchangeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
