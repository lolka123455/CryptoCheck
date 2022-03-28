package com.example.cryptocheck.data.network

import com.example.cryptocheck.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptocheck.data.network.model.CoinNamesListDto
import retrofit2.http.GET
import retrofit2.http.Query

/*This interface is getting the top 10 coins from CoinMarketCap and their current price in USD.
It also gets the full list of all coins with their prices in USD.*/
interface ApiService {
    @GET("top/totalvolfull")
   suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String =
            "f25aa66b2ccc2607eb1ecf26b8ce61db95d6462d622325a45c37e15f38504359",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String =
            "f25aa66b2ccc2607eb1ecf26b8ce61db95d6462d622325a45c37e15f38504359",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String
    ): CoinInfoJsonContainerDto

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}