package com.example.cryptocheck.di

import android.app.Application
import com.example.cryptocheck.presentation.CoinApp
import com.example.cryptocheck.presentation.CoinDetailFragment
import com.example.cryptocheck.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

/*This code is creating a Dagger component that will be used to inject dependencies into the
ViewModelFactory. The module for DataModule and WorkerModule are included in this code block because
they contain all of the classes needed by the ViewModelFactory. This includes data sources,
repositories, and other classes that need to be injected into the ViewModelFactory.
The module for ViewModelModule contains all of the classes needed by each view model class (e.g., MainViewModel).*/
@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class]
)
/*This interface is creating a Dagger ApplicationComponent.Factory interface that will be used to
create an instance of the ApplicationComponent class. The @BindsInstance annotation tells Dagger
which application instance should be injected into this component. This allows us to inject the
application context into our components without having to explicitly pass it in as a constructor
parameter, or using some other method like getApplicationContext().*/
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    /*This interface is creating a new instance of the ApplicationComponent. This component will be
    used to create instances of all other components that are needed for this application.*/
    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}