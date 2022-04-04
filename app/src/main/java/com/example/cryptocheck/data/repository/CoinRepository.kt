package com.example.cryptocheck.data.repository

import androidx.lifecycle.LiveData
import com.example.cryptocheck.data.network.model.CoinInfo

//This interface is getting the list of coin info from the api and storing it in a local database.

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>

    fun loadData()
}