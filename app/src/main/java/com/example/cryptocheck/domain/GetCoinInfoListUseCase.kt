package com.example.cryptocheck.domain

//This class is calling the repository to get a list of coin info

class GetCoinInfoListUseCase(

    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}