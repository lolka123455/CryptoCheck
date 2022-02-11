package com.example.cryptocheck.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptocheck.data.network.ApiFactory
import com.google.gson.Gson
import com.example.cryptocheck.data.database.AppDatabase
import com.example.cryptocheck.data.database.CoinInfoDbModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.cryptocheck.data.network.model.CoinInfoDto
import com.example.cryptocheck.data.network.model.CoinInfoJsonContainerDto
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    @SuppressLint("LongLogTag")
    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
            .map { it.names?.map { it.coinName?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it.toString()) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it != null) {
                    db.coinPriceInfoDao().insertPriceList(it)
                    Log.d("SUCCESSFUL TEST LOADING DATA", it.toString())
                }
            }, {
                Log.d("FAILED TEST LOADING DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun getPriceListFromRawData(coinInfoJsonContainerDto: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = ArrayList<CoinInfoDto>()
        val jsonObject = coinInfoJsonContainerDto.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }
}