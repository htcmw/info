package com.info.coin.client.feign.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.info.coin.business.constant.CautionType;
import com.info.coin.business.constant.CryptoCurrencyType;
import com.info.coin.business.domain.CoinName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
@JsonDeserialize(using = UpbitCoinListResponseDtoDeserializer.class)
public class UpbitCoinListResponseDto {

    private List<UpbitCoinListDto> upbitCoinList;

    public Map<CoinName, Set<CryptoCurrencyCautionDto>> formalizeCoinInfo() {
        Map<CoinName, Set<CryptoCurrencyCautionDto>> upbitInfo = new HashMap<>();

        for (UpbitCoinListDto coinInfo : upbitCoinList) {
            String[] str = coinInfo.getMarket().split("-");
            String symbol = str[1];
            String korean = coinInfo.getKoreanName();
            String english = coinInfo.getEnglishName();
            CryptoCurrencyType cryptoCurrency = CryptoCurrencyType.valueOf(str[0]);
            CautionType marketWarning = coinInfo.getMarketWarning();

            CoinName coinName = CoinName.builder()
                    .symbol(symbol)
                    .korean(korean)
                    .english(english)
                    .build();
            CryptoCurrencyCautionDto cryptoCurrencyCaution = CryptoCurrencyCautionDto.builder()
                    .cryptoCurrency(cryptoCurrency)
                    .caution(marketWarning)
                    .build();

            Set<CryptoCurrencyCautionDto> cryptoCurrencyCautions = upbitInfo.getOrDefault(coinName, new HashSet<>());
            cryptoCurrencyCautions.add(cryptoCurrencyCaution);
            upbitInfo.put(coinName, cryptoCurrencyCautions);
        }
        return upbitInfo;
    }

}
