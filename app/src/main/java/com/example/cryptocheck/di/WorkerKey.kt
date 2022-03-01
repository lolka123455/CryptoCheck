package com.example.cryptocheck.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey (val value: KClass<out ListenableWorker>)