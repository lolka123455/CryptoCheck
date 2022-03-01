package com.example.cryptocheck.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//This data class is getting the coin name from CoinNameContainerDto and storing it in a variable.
data class CoinNameContainerDto(

    @SerializedName("CoinInfo")
    @Expose
    val coinName: CoinNameDto? = null
)