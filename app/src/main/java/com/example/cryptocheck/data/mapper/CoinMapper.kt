package com.example.cryptocheck.data.mapper

import com.example.cryptocheck.data.database.CoinInfoDbModel
import com.example.cryptocheck.data.network.model.CoinInfoDto
import com.example.cryptocheck.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptocheck.data.network.model.CoinNamesListDto
import com.example.cryptocheck.domain.CoinInfo
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/*This class is mapping the data from the API to a list of CoinInfoDto.
The mapJsonContainerToListCoinInfo function takes in a json container and returns a list of
CoinInfoDto. It iterates through each coin key, which represents one currency (e.g., BTC).
It then iterates through each currency key, which represents another type of currency (e.g., USD).*/
class CoinMapper @Inject constructor(){

    //This function is mapping the data from CoinInfoDto to CoinInfoDbModel.
    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        highDay = dto.highDay,
        lowDay = dto.lowDay,
        lastMarket = dto.lastMarket,
        imageUrl = BASE_IMAGE_URL + dto.imageUrl
    )

    /*This function is doing the following. It's creating a list of CoinInfoDto objects and adding
    them to it. The jsonObject that we get from the JsonContainerDto object is then being converted
    into a Map String> type using Gson().fromJson() method.*/
    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    /*This function is mapping the names list to a string. The map function takes in an element of
    type CoinNamesListDto and returns a String. The joinToString function joins all elements of the
    array into one string separated by commas.
    If there are no elements, it will return an empty string.*/
    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    //This function is converting the database model to an entity.
    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = convertTimestampToTime(dbModel.lastUpdate),
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl
    )

    /*This function is converting the timestamp to a time. The pattern is "HHmmss" which means that
    it will show hours, minutes and seconds in 24 hour format. The locale is set to default so that
    it can be used on any device.
    This code also sets the timezone of the system as default for this app.*/
    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}