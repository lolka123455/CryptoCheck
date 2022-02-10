package com.example.cryptocheck.data.network

import io.reactivex.rxjava3.core.Single
import com.example.cryptocheck.data.model.CoinInfoListOfData
import com.example.cryptocheck.data.model.CoinPriceInfoRawData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String =
            "87446bc49db98981449dd79a2a4aa9228b4bafaf0e85e40c0fcd9db59c55f30e",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String =
            "87446bc49db98981449dd79a2a4aa9228b4bafaf0e85e40c0fcd9db59c55f30e",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String
    ): Single<CoinPriceInfoRawData>

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }

}