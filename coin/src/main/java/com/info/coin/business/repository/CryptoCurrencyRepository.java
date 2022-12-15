package com.info.coin.business.repository;

import com.info.coin.business.domain.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    Set<CryptoCurrency> findByCoinExchangeIdx(Long coinExchangeIdx);
}
