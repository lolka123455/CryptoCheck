package com.example.cryptocheck.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

//This data class is getting the name of a coin from Cryptocompare.
data class CoinNameDto(

    @SerializedName("Name")
    @Expose
    val name: String? = null
)