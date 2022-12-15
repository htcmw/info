package com.info.coin.business.service;


import com.info.coin.business.constant.CautionType;
import com.info.coin.business.constant.CoinExchangeStatus;
import com.info.coin.business.constant.CryptoCurrencyType;
import com.info.coin.business.constant.ExchangeType;
import com.info.coin.business.domain.*;
import com.info.coin.business.repository.*;
import com.info.coin.client.feign.UpbitFeignClient;
import com.info.coin.client.feign.dto.CryptoCurrencyCautionDto;
import com.info.coin.client.feign.dto.UpbitCoinListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpbitCoinsRegisterService {
    private final UpbitFeignClient upbitFeignClient;
    private final CoinRepository coinRepository;
    private final CoinExchangeRepository coinExchangeRepository;
    private final ExchangeRepository exchangeRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CautionRepository cautionRepository;

    @Transactional
    public void run() {
        UpbitCoinListResponseDto coinInfoList = upbitFeignClient.getAllMarkets(true);
        Map<CoinName, Set<CryptoCurrencyCautionDto>> upbitInfo = coinInfoList.formalizeCoinInfo();

        if (!exchangeRepository.existsByName(ExchangeType.UPBIT)) { //모든 코인 등록
            Exchange exchange = exchangeRepository.save(Exchange.builder().name(ExchangeType.UPBIT).build());

            for (Map.Entry<CoinName, Set<CryptoCurrencyCautionDto>> entry : upbitInfo.entrySet()) {
                CoinName coinName = entry.getKey();
                Set<CryptoCurrencyCautionDto> cryptoCurrencyCautions = entry.getValue();
                registerCoin(exchange, coinName, cryptoCurrencyCautions);
            }
            return;
        }

        //모든 코인 점검
        Set<CoinExchange> coinExchanges = coinExchangeRepository.findByExchangeName(ExchangeType.UPBIT);
        for (CoinExchange coinExchange : coinExchanges) {

            if (!existCoinData(upbitInfo, coinExchange)) continue;

            for (CryptoCurrencyCautionDto cryptoCurrencyCaution : upbitInfo.get(coinExchange.getCoin().getName())) {

                Set<CryptoCurrency> currencies = coinExchange.getCurrencies();
                CryptoCurrency cryptoCurrency = findCryptoCurrency(currencies, cryptoCurrencyCaution.getCryptoCurrency());

                if (cryptoCurrency == null) {
                    cryptoCurrency = cryptoCurrencyRepository.save(CryptoCurrency.builder()
                            .type(cryptoCurrencyCaution.getCryptoCurrency())
                            .coinExchange(coinExchange)
                            .build());
                }

                if (cryptoCurrency.getCautions() == null && cryptoCurrencyCaution.getCaution().equals(CautionType.CAUTION)) {
                    cautionRepository.save(Caution.builder()
                            .currency(cryptoCurrency)
                            .build());
                } else {
                    Caution latestCaution = Collections.max(cryptoCurrency.getCautions(), (a, b) -> b.getCreateAt().compareTo(a.getCreateAt()));

                    if (cryptoCurrencyCaution.getCaution().equals(CautionType.CAUTION) && latestCaution.getCancelAt() != null) {
                        coinExchange.changeStatus(CoinExchangeStatus.CAUTION_MISMATCH);
                        cautionRepository.save(Caution.builder()
                                .currency(cryptoCurrency)
                                .build());
                    } else if (cryptoCurrencyCaution.getCaution().equals(CautionType.NONE) && latestCaution.getCancelAt() == null) {
                        coinExchange.changeStatus(CoinExchangeStatus.CAUTION_MISMATCH);
                    }
                }
            }
        }
    }

    private void registerCoin(Exchange exchange, CoinName name, Set<CryptoCurrencyCautionDto> cryptoCurrencyCautions) {
        Coin coin = coinRepository.findBySymbol(name.getSymbol())
                .orElse(coinRepository.save(Coin.builder()
                        .name(CoinName.builder()
                                .symbol(name.getSymbol())
                                .english(name.getEnglish())
                                .korean(name.getKorean())
                                .build())
                        .build()));
        coin.fillKoreanName(name.getKorean());
        coin.fillEnglishName(name.getEnglish());

        CoinExchange coinExchange = coinExchangeRepository.save(CoinExchange.builder()
                .coin(coin)
                .exchange(exchange)
                .build());

        for (CryptoCurrencyCautionDto cryptoCurrencyCaution : cryptoCurrencyCautions) {
            CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.save(CryptoCurrency.builder()
                    .coinExchange(coinExchange)
                    .type(cryptoCurrencyCaution.getCryptoCurrency())
                    .build());
            if (cryptoCurrencyCaution.getCaution().equals(CautionType.CAUTION)) {
                cautionRepository.save(Caution.builder()
                        .currency(cryptoCurrency)
                        .build());
            }
        }
    }

    private boolean existCoinData(Map<CoinName, Set<CryptoCurrencyCautionDto>> upbitInfo, CoinExchange coinExchange) {
        CoinName name = coinExchange.getCoin().getName();
        if (!upbitInfo.containsKey(name)) {
            coinExchange.changeStatus(CoinExchangeStatus.NO_UPBIT_DATA);
            log.warn("{} 거래소에서 {} 코인에 대한 데이터가 내려오지 않습니다.", ExchangeType.UPBIT.name(), name.getSymbol());
            return false;
        }
        return true;
    }

    private CryptoCurrency findCryptoCurrency(Set<CryptoCurrency> currencies, CryptoCurrencyType type) {
        for (CryptoCurrency cryptoCurrency : currencies) {
            if (cryptoCurrency.getType().equals(type))
                return cryptoCurrency;
        }
        return null;
    }

}
