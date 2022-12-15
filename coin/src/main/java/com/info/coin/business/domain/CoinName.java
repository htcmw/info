package com.info.coin.business.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CoinName {

    @Column(nullable = false, unique = true)
    private String symbol;

    @Setter
    private String korean;

    @Setter
    private String english;

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;
        CoinName object = (CoinName) o;
        return this.getSymbol().equals(object.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSymbol());
    }
}
