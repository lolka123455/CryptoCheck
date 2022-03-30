package com.example.cryptocheck.di

import android.app.Application
import com.example.cryptocheck.data.database.AppDatabase
import com.example.cryptocheck.data.database.CoinInfoDao
import com.example.cryptocheck.data.network.ApiFactory
import com.example.cryptocheck.data.network.ApiService
import com.example.cryptocheck.data.repository.CoinRepositoryImpl
import com.example.cryptocheck.data.database.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

/*This code is binding the CoinRepositoryImpl class to the interface CoinRepository.
The @ApplicationScope annotation tells Dagger that this dependency should be provided once and only
once for all objects in an application. This means that if you have multiple instances of your app,
they will share a single instance of this object. It also binds the coinInfoDao() method from
AppDatabase to provideCoinInfoDao().*/
@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object{

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }

}