package com.example.cryptocheck.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptocheck.domain.GetCoinInfoListUseCase
import com.example.cryptocheck.domain.GetCoinInfoUseCase
import com.example.cryptocheck.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {

        loadDataUseCase()
    }
}