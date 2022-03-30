package com.example.cryptocheck.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_coin_info")
data class CoinInfo(
    @PrimaryKey(autoGenerate = true)
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: String,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String
)