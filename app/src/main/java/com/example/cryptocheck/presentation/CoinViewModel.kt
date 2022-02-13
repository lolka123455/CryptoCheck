package com.example.cryptocheck.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cryptocheck.data.repository.CoinRepositoryImpl
import com.example.cryptocheck.domain.GetCoinInfoListUseCase
import com.example.cryptocheck.domain.GetCoinInfoUseCase
import com.example.cryptocheck.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    val coinInfoList = getCoinInfoListUseCase()
    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {

        loadDataUseCase()
    }
}