package com.info.coin.business.repository;

import com.info.coin.business.domain.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    @Query("select c " +
            "from Coin c " +
            "where c.name.symbol = :symbol")
    Optional<Coin> findBySymbol(@Param("symbol") String symbol);
}
