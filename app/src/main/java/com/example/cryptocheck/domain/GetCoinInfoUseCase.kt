package com.example.cryptocheck.domain

//This class is calling the repository to get coin info.

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}