package com.example.cryptocheck.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

//This data class is getting the coin names from the API and storing them in a list.
data class CoinNamesListDto(

    @SerializedName("Data")
    @Expose
    val names: List<CoinNameContainerDto>? = null
)