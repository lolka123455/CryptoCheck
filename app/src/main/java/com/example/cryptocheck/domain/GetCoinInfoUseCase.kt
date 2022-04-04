package com.example.cryptocheck.domain

import com.example.cryptocheck.data.repository.CoinRepository
import javax.inject.Inject

//This class is calling the repository to get coin info.

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}