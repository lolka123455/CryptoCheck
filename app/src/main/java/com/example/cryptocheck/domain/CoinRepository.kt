package com.example.cryptocheck.domain

import androidx.lifecycle.LiveData

//This interface is getting a list of CoinInfo objects from the database

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>
}