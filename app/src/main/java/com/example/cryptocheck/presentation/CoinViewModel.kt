package com.example.cryptocheck.presentation

import androidx.lifecycle.ViewModel
import com.example.cryptocheck.domain.GetCoinInfoListUseCase
import com.example.cryptocheck.domain.GetCoinInfoUseCase
import com.example.cryptocheck.domain.LoadDataUseCase
import javax.inject.Inject

/*This class is calling the GetCoinInfoListUseCase and then setting it to a LiveData variable.
The reason for this is because we want to be able to observe changes in the list of coins, so we can
update our UI accordingly. We also need to call loadDataUseCase() at initialization time, which will
trigger an asynchronous task that will fetch data from Cryptocompare API.*/
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