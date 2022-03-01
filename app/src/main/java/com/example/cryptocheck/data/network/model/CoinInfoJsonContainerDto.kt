package com.example.cryptocheck.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

/*This data class is parsing the JSON object and returning a CoinInfoJsonContainerDto.
The JsonObject is then used to create a new instance of the class CoinInfoJsonContainerDto.*/
data class CoinInfoJsonContainerDto(

    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)