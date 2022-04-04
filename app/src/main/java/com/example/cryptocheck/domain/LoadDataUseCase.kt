package com.example.cryptocheck.domain

import com.example.cryptocheck.data.repository.CoinRepository
import javax.inject.Inject

//This class is calling the repository's loadData() method.
class LoadDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.loadData()
}