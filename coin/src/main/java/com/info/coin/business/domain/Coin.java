package com.info.coin.business.domain;

import com.info.coin.business.audit.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COINS", indexes = @Index(name = "index_coin_symbol", columnList = "symbol"))
public class Coin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Embedded
    private CoinName name;

    public void fillKoreanName(String korean) {
        if (name.getKorean() == null) name.setKorean(korean);
    }

    public void fillEnglishName(String english) {
        if (name.getEnglish() == null) name.setEnglish(english);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;
        Coin object = (Coin) o;
        return this.getName().equals(object.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
