package com.example.cryptocheck.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

//This class is creating a map of ViewModels
@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value : KClass<out ViewModel>)
