package com.example.cryptocheck.di

import androidx.lifecycle.ViewModel
import com.example.cryptocheck.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*This code is binding the CoinViewModel class to the ViewModelKey annotation.
The @Binds annotation tells Dagger that this method will provide a concrete implementation of an
interface, and it should be stored in a map where keys are view model classes and values are their
corresponding implementations. This code also uses the @IntoMap annotation which allows us to
specify how we want our keyvalue pairs to be stored in our map.*/
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(viewModel: CoinViewModel): ViewModel
}