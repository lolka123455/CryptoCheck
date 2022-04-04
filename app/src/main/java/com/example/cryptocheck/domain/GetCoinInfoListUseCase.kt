package com.example.cryptocheck.domain

import com.example.cryptocheck.data.repository.CoinRepository
import javax.inject.Inject

//This class is calling the repository to get a list of coin info

class GetCoinInfoListUseCase @Inject constructor(

    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}