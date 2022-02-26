package com.example.cryptocheck.di

import android.app.Application
import com.example.cryptocheck.data.database.AppDatabase
import com.example.cryptocheck.data.database.CoinInfoDao
import com.example.cryptocheck.data.repository.CoinRepositoryImpl
import com.example.cryptocheck.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object{

        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }

}