package com.info.coin.business.repository;

import com.info.coin.business.constant.ExchangeType;
import com.info.coin.business.domain.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Optional<Exchange> getExchangesByName(ExchangeType name);

    Boolean existsByName(ExchangeType name);
}
