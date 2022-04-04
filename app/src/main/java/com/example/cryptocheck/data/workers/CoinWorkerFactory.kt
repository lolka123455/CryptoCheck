package com.example.cryptocheck.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

/*This code is creating a new instance of the RefreshDataWorker class and passing in the appContext,
workerParameters. The childWorkerFactory variable is then set to be equal to the provider that was
created for this specific type of Worker. Finally, we return the create method from our
ChildWorkerFactory which will return an instance of our RefreshDataWorker class*/
class CoinWorkerFactory @Inject constructor(
    /*This code is creating a map of classes to providers. The key is the class that we want to
    create, and the value is the provider for that class. This allows us to inject our own custom
     ChildWorkerFactory into each instance of our CoinWorkerFactory. We can then use this in our
     onCreateWorker method below.*/
    private val workerProvider: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    /*This function is creating a new instance of the RefreshDataWorker class. The workerClassName
    parameter is passed to this function and it's value will be equal to the qualified name of the
    RefreshDataWorker class. This code then checks if there is an implementation for that particular
    Worker in our map, which we created earlier. If there isn't one, it returns null.*/
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        val foundEntry =
            workerProvider.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val factoryProvider = foundEntry?.value
            ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
        return factoryProvider.get().create(appContext, workerParameters)
    }
}

//override fun createWorker(
//    appContext: Context,
//    workerClassName: String,
//    workerParameters: WorkerParameters
//): ListenableWorker? {
//    return when (workerClassName) {
//        RefreshDataWorker::class.qualifiedName -> {
//            val childWorkerFactory = workerProvider[RefreshDataWorker::class.java]?.get()
//            return childWorkerFactory?.create(appContext,workerParameters)
//        }
//        else -> null
//    }
//}