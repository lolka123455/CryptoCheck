package com.example.cryptocheck.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*This code is creating a database named AppDatabase. The @Database annotation tells Room that this
database has one table, CoinInfoDbModel.The version number of the database is 2 because we are
using the fallbackToDestructiveMigration() method to migrate from version 1 to version 2 of our
database schema. */
@Database(entities = [CoinInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /*This function is creating a CoinInfoDao interface. The CoinInfoDao interface has one abstract
     method named coinPriceInfoDao(). This abstract method returns the type of CoinInfoDao.*/
    abstract fun coinPriceInfoDao(): CoinInfoDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        operator fun invoke(context: Context) = db ?: synchronized(LOCK) {
            db ?: getInstance(context).also { db = it }
        }

        fun getInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
    }
}
