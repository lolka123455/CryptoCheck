package com.example.cryptocheck.domain


import org.junit.Assert.assertEquals
import org.junit.Test

//data class CoinInfo(
//    val fromSymbol: String,
//    val toSymbol: String?,
//    val price: String?,
//    val lastUpdate: String,
//    val highDay: String?,
//    val lowDay: String?,
//    val lastMarket: String?,
//    val imageUrl: String
//)

class CoinInfoTest {


    @Test
    fun coinInfo_fromSymbol_toSymbol() {

        val coinInfo = CoinInfo(
            "BTC",
            "USD",
            "2500.00",
            "2022-01-01",
            "23000.00",
            "18000.00",
            "OKEX",
            "https://cryptocompare.com"
        )

        assertEquals("BTC", coinInfo.fromSymbol)

        assertEquals("USD", coinInfo.toSymbol)

        assertEquals("2500.00", coinInfo.price)

        assertEquals("2022-01-01", coinInfo.lastUpdate)

        assertEquals("23000.00", coinInfo.highDay)

        assertEquals("18000.00", coinInfo.lowDay)

        assertEquals("OKEX", coinInfo.lastMarket)

        assertEquals(
            "https://cryptocompare.com",
            coinInfo.imageUrl
        )
    }

    @Test
    fun coinInfo_price_update() {
        val coinInfo = CoinInfo(
            "BTC",
            "USD",
            "2500.00",
            "2022-01-01",
            "23000.00",
            "18000.00",
            "OKEX",
            "https://cryptocompare.com"
        )

        assertEquals("BTC", coinInfo.fromSymbol)

        assertEquals("USD", coinInfo.toSymbol)

        assertEquals("2500.00", coinInfo.price)

        assertEquals("2022-01-01", coinInfo.lastUpdate)

        assertEquals("23000.00", coinInfo.highDay)

        assertEquals("18000.00", coinInfo.lowDay)

        assertEquals("OKEX", coinInfo.lastMarket)

        assertEquals(
            "https://cryptocompare.com",
            coinInfo.imageUrl
        )
    }

    @Test
    fun coinInfo_toSymbol_imageUrl() {

        val coinInfo = CoinInfo(
            "BTC",
            "USD",
            "2500.00",
            "2022-01-01",
            "23000.00",
            "18000.00",
            "OKEX",
            "https://cryptocompare.com"
        )

        assertEquals("USD", coinInfo.toSymbol)

        assertEquals(
            "https://cryptocompare.com",
            coinInfo.imageUrl
        )
    }

}