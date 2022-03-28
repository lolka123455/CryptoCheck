package com.example.cryptocheck.data.workers

import android.content.Context
import androidx.work.*
import com.example.cryptocheck.data.database.CoinInfoDao
import com.example.cryptocheck.data.mapper.CoinMapper
import com.example.cryptocheck.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

/*This class is doing the following. It's creating a new instance of RefreshDataWorker class and
passing in the context, workerParameters, coinInfoDao, apiService and mapper as parameters to it.
Then we are calling doWork() method on this object which will be executed by Android Work Manager.
This method contains an infinite loop that runs until interrupted or killed by user.*/
class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {

    /*This suspend function is doing the following. It's using a service to get top 50 coins from
    cryptocompare and map it to a list of strings (fSyms) It uses another service to get full price
    info for all (fSyms) in one call, then maps that json container into a list of CoinInfoDto objects.
    Then it converts each CoinInfoDto object into an instance of DbModel class which can be inserted
    into the database.*/
    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(60000) // 1 minute
        }
    }

    companion object {

        const val NAME = "RefreshDataWorker"

        /*This function is creating a OneTimeWorkRequest. This means that the work will only be run
        once, and then it will stop running. The RefreshDataWorker class is being passed into the
        builder as an argument to create this request.*/
        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    /*This class is creating a new class called RefreshDataWorker. This class extends the
    ListenableWorker and implements the Worker interface. The constructor of this class takes in
    three parameters, context, workerParameters, coinInfoDao, apiService and mapper.*/
    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinMapper
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                coinInfoDao,
                apiService,
                mapper
            )
        }
    }
}