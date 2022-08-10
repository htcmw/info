package com.invest.coin.repository;

import com.invest.coin.constant.ExchangeType;
import com.invest.coin.entity.CoinExchange;
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
