package com.example.cryptocheck.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*This class is creating a database named main.db and using the RoomDatabase annotation to create
an instance of AppDatabase and return it when getInstance() is called.
The fallbackToDestructiveMigration() method will delete the existing database
if there's any change in version number, so that we can always have our latest data
stored in our app. */
@Database(entities = [CoinInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /*This code is creating a companion object that has the db variable and the DB_NAME constant.
     The db variable is set to null, which means it's not yet initialized. The LOCK object is used
     as a lock for thread safety. When we call getInstance() method, if the database hasn't been
     created yet, then this code will create one by calling
     AppDatabase::class.getConstructor().newInstance(). This creates an instance of our database
     class (AppDatabase).*/
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()
        /*This function is creating a new instance of the database if it doesn't exist.
         If it does, then we return that instance.*/
        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    /*This function is creating a CoinInfoDao interface. The CoinInfoDao interface has one abstract
     method named coinPriceInfoDao(). This abstract method returns the type of CoinInfoDao.*/
    abstract fun coinPriceInfoDao(): CoinInfoDao
}
