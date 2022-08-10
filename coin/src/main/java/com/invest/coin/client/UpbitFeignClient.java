package com.invest.coin.client;


import com.invest.coin.client.dto.UpbitCoinListResponseDto;
import com.invest.coin.config.UpbitClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "upbit", url = "${openapi.upbit}", configuration = UpbitClientConfig.class)
public interface UpbitFeignClient {
    @GetMapping("/market/all")
    UpbitCoinListResponseDto getAllCointAllMarket(@RequestParam("isDetails") boolean isDetails);
}
