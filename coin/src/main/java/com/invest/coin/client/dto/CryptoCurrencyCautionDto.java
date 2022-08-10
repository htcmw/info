package com.invest.coin.client.dto;

import com.invest.coin.constant.CautionType;
import com.invest.coin.constant.CryptoCurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class CryptoCurrencyCautionDto {
    private final CryptoCurrencyType cryptoCurrency;
    private final CautionType caution;

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;
        CryptoCurrencyCautionDto object = (CryptoCurrencyCautionDto) o;
        return this.getCryptoCurrency().equals(object.getCryptoCurrency()) && this.getCaution().equals(object.getCaution());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCaution()) + Objects.hashCode(getCryptoCurrency());
    }
}
