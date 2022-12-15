package com.info.coin.business.service;

import com.info.coin.business.repository.CoinRepository;
import com.info.coin.component.UpbitCoinCurrencyLinker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpbitCoinService {
    private final CoinRepository coinRepository;

    public List<String> getMarkets() {
        var markets = coinRepository.findAll().stream()
                .map(coin -> coin.getName().getSymbol())
                .toList();
        return UpbitCoinCurrencyLinker.link(markets);
    }
}
