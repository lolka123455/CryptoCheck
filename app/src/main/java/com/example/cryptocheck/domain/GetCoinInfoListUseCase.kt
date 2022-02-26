package com.example.cryptocheck.domain

import javax.inject.Inject

//This class is calling the repository to get a list of coin info

class GetCoinInfoListUseCase @Inject constructor(

    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}