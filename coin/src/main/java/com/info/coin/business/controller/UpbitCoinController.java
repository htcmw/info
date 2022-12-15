package com.info.coin.business.controller;

import com.info.coin.business.dto.MarketResponseDto;
import com.info.coin.business.service.UpbitCoinService;
import com.info.coin.business.service.UpbitCoinsRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/coins")
@RequiredArgsConstructor
public class UpbitCoinController {

    private final UpbitCoinService upbitCoinService;
    private final UpbitCoinsRegisterService upbitCoinsRegisterService;

    @PutMapping
    public ResponseEntity<Void> registerCoins() {
        upbitCoinsRegisterService.run();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<MarketResponseDto> getCoins() {
        var result = upbitCoinService.getMarkets();
        return ResponseEntity.ok().body(MarketResponseDto.of(result));
    }
}
