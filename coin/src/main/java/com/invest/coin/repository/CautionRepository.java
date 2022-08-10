package com.invest.coin.repository;


import com.invest.coin.entity.Caution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CautionRepository extends JpaRepository<Caution, Long> {
}
