package com.info.coin.business.repository;

import com.info.coin.business.constant.ExchangeType;
import com.info.coin.business.domain.CoinExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CoinExchangeRepository extends JpaRepository<CoinExchange, Long> {

    @Query("SELECT ce " +
            "FROM CoinExchange ce JOIN ce.exchange cee " +
            "JOIN ce.coin ccn " +
            "JOIN ce.currencies ccy " +
            "JOIN ccy.cautions ccs " +
            "WHERE cee.name = :exchangeName")
    Set<CoinExchange> findByExchangeName(@Param("exchangeName") ExchangeType exchangeName);
}
