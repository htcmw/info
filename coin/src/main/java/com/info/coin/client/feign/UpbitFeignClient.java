package com.info.coin.client.feign;


import com.info.coin.client.feign.dto.UpbitCoinListResponseDto;
import com.info.coin.config.UpbitClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "upbit", url = "${external-api.upbit.openapi}", configuration = UpbitClientConfig.class)
public interface UpbitFeignClient {
    @GetMapping("/market/all")
    UpbitCoinListResponseDto getAllMarkets(@RequestParam("isDetails") boolean isDetails);
}
