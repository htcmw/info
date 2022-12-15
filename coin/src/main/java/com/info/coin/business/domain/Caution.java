package com.info.coin.business.domain;

import com.info.coin.business.audit.BaseEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Caution extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CRYPTO_CURRENCIES_IDX")
    private CryptoCurrency currency;

    private String reason;

    @Column(insertable = false)
    private LocalDateTime cancelAt;

    @Builder
    public Caution(Long idx, CryptoCurrency currency, String reason) {
        this.idx = idx;
        this.currency = currency;
        this.reason = reason;
    }
}
