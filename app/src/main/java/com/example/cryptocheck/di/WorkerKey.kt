package com.example.cryptocheck.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

/*This class is using the WorkerKey annotation to map a class of type ListenableWorker to an ID.
The value parameter in the annotation specifies which class we want to use as our key.
This allows us to have multiple classes that extend from ListenableWorker and specify their own
unique IDs for each one.*/
@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)