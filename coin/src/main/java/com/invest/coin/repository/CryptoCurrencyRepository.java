package com.invest.coin.repository;

import com.invest.coin.entity.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    Set<CryptoCurrency> findByCoinExchangeIdx(Long coinExchangeIdx);
}
