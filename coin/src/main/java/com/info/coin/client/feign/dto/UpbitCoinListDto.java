package com.info.coin.client.feign.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.info.coin.business.constant.CautionType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpbitCoinListDto {
    private String market;          //업비트에서 제공중인 시장 정보
    private String koreanName;     //거래 대상 암호화폐 한글명
    private String englishName;    //거래 대상 암호화폐 영문명
    private CautionType marketWarning;  //유의 종목 여부: NONE (해당 사항 없음), CAUTION(투자유의)
}
