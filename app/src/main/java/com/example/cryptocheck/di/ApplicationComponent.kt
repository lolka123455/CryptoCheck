package com.example.cryptocheck.di

import android.app.Application
import com.example.cryptocheck.presentation.CoinApp
import com.example.cryptocheck.presentation.CoinDetailFragment
import com.example.cryptocheck.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    WorkerModule::class]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent

    }
}