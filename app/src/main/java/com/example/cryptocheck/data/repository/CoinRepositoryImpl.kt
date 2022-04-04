package com.example.cryptocheck.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptocheck.data.database.CoinInfoDao
import com.example.cryptocheck.data.mapper.CoinMapper
import com.example.cryptocheck.data.network.model.CoinInfo
import com.example.cryptocheck.data.workers.RefreshDataWorker
import javax.inject.Inject

/*This class is creating a new instance of WorkManager. Then, it enqueues the work request to
refresh data from API and save it in database. The last thing is that we are using Transformations
to map LiveData objects into other types. In this case, we are mapping List object into list of
CoinInfo objects and then returning them as LiveData object.*/
class CoinRepositoryImpl @Inject constructor(
    private val coinInfoDao:CoinInfoDao,
    private val mapper: CoinMapper,
    private val application: Application
) : CoinRepository {

    //This function is mapping the database model to a list of CoinInfo objects.
    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    //This function is mapping the data from database to entity
    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    /*This function is creating a new work request to refresh the data. The name of this work request
    is RefreshDataWorker.NAME, and it will replace any existing work with that name. This code also
    creates a new WorkRequest object using the makeRequest() method in the RefreshDataWorker class,
    which returns an instance of WorkRequest.*/
    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}