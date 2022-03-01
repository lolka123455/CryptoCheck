package com.example.cryptocheck.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptocheck.data.workers.CoinWorkerFactory
import com.example.cryptocheck.di.DaggerApplicationComponent
import javax.inject.Inject

/*This class is creating a DaggerApplicationComponent.factory() and then calling create on it,
passing in the application context as an argument. The factory method returns a new instance of
ApplicationComponent that has been created with the application context passed into it.
This component will be used to inject CoinApp by using its inject method, which takes in CoinApp as
an argument.*/
class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    //This function is creating a new instance of the [AppComponent] class and injecting it into this activity.
    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    /*This function is setting up the work manager configuration. The Configuration Builder class is
    used to configure the WorkManager instance. It's a builder pattern, so we can set multiple
    properties at once and then build it with `build()`. We're also overriding
    getWorkManagerConfiguration() in our AppModule class to return this new configuration object.*/
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}