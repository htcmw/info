package com.info.coin.component;

import com.info.coin.business.constant.CryptoCurrencyType;

import java.util.ArrayList;
import java.util.List;

public class UpbitCoinCurrencyLinker {
    public static List<String> link(List<String> markets) {
        var result = new ArrayList<String>();
        for (var market : markets) {
            result.add(CryptoCurrencyType.KRW + "-" + market);
            result.add(CryptoCurrencyType.BTC + "-" + market);
        }
        return result;
    }
}
