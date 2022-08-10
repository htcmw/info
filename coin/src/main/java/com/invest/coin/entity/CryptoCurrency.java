package com.invest.coin.entity;

import com.invest.coin.constant.CryptoCurrencyType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "CRYPTO_CURRENCIES")
public class CryptoCurrency extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private CryptoCurrencyType type;

    @OneToMany(mappedBy = "currency", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Caution> cautions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COIN_EXCHANGE_IDX")
    private CoinExchange coinExchange;

    private LocalDateTime delistedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;
        CryptoCurrency object = (CryptoCurrency) o;
        return this.getType().equals(object.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getType());
    }
}
