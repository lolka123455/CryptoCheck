package com.example.cryptocheck.presentation.adapters

import com.example.cryptocheck.domain.CoinInfo
import junit.framework.Assert.assertTrue
import org.junit.Test

class CoinInfoDiffCallbackTest {

    @Test
    fun areItemsTheSame() {
        val oldItem = CoinInfo(
            "BTC",
            "UDS",
            "2500.00",
            "2022-01-01",
            "23000.00",
            "18000.00",
            "OKEX",
            "https://www.coinmarketcap.com/assets/img/coins/btc.png"
        )
        val newItem = CoinInfo(
            "BTC",
            "UDS",
            "2000.00",
            "2022-01-01",
            "23000.00",
            "18000.00",
            "OKEX",
            "https://www.coinmarketcap.com/assets/img/coins/btc.png"
        )
        assertTrue(oldItem.fromSymbol==newItem.fromSymbol)
    }

    @Test fun areContentsTheSame() {
        val oldItem = CoinInfo(
            "BTC",
            "UDS",
            "2500.00",
            "2022-01-01",
            "23000.00",
            "18000.00",
            "OKEX",
            "https://cryptocompare.com"
        )
        val newItem = CoinInfo(
            "BTC",
            "UDS",
            "2500.00",
            "2022-01-01",
            "23000.00",
            "18000.00",
            "OKEX",
            "https://cryptocompare.com"
        )
        assertTrue(oldItem == newItem)
    }
}
