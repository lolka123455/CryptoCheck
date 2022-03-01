package com.example.cryptocheck.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {

    /*This function is creating a new instance of the ListenableWorker class. The constructor takes
    in two parameters, context and workerParameters is an Android Context object that allows us to
    access resources from our app. workerParameters are the parameters passed into this Worker's
    constructor when it was created by WorkManager.*/
    fun create(
        context: Context,
        workerParameters: WorkerParameters
    ): ListenableWorker
}