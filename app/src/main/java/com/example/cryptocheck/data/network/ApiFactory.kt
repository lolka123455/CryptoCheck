package com.example.cryptocheck.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*This object is creating an instance of the ApiService class. The Retrofit library uses Gson to
convert JSON data into Kotlin objects. This code creates a base URL for the API service, which in
this case is https:/minapi.cryptocompare.com/data/.*/
object ApiFactory {

    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"

    /*This code is creating a Retrofit object. The `addConverterFactory` method adds the Gson
    converter factory to the retrofit builder. The `baseUrl` method sets the base url for our API
    call, which in this case is "https://min-api.cryptocompare.com/data/". Finally, we build our
    Retrofit object using the .build() method and assign it to a variable called retrofit.*/
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}