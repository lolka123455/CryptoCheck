package com.example.cryptocheck.presentation

import android.app.Application
import com.example.cryptocheck.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}