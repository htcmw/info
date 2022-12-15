package com.info.coin.business.repository;


import com.info.coin.business.domain.Caution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CautionRepository extends JpaRepository<Caution, Long> {
}
