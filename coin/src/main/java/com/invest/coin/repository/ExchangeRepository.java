package com.invest.coin.repository;

import com.invest.coin.constant.ExchangeType;
import com.invest.coin.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Optional<Exchange> getExchangesByName(ExchangeType name);

    Boolean existsByName(ExchangeType name);
}
