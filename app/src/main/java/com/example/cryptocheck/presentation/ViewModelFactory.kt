package com.example.cryptocheck.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/*This class is creating a map of ViewModels and their providers. The key is the class of the
ViewModel, and the value is its provider. This allows us to create new instances of our view models
by simply calling `viewModelProviders[modelClass]?.get()` where modelClass is the class we want to
get an instance for.*/
class ViewModelFactory @Inject constructor(
    private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>,
            Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
    }
}
