package com.example.cryptocheck.di

import com.example.cryptocheck.data.workers.ChildWorkerFactory
import com.example.cryptocheck.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//This interface is binding the RefreshDataWorker
@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}