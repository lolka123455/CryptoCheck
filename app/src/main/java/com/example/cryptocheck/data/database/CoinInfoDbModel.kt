package com.example.cryptocheck.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/*This data class is creating a table in the database. The @Entity annotation tells the Room to
create this table with these columns and their types. The primary key is set to fromSymbol,
which will be used as a foreign key for other tables that reference it. toSymbol and price are
optional because they may not always exist on each coin's page (e.g., Bitcoin Cash).*/
@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: Long?,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String
)