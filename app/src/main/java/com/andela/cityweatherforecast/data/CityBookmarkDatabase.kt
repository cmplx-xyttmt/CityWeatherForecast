package com.andela.cityweatherforecast.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityBookmarkDatabase : RoomDatabase() {

    abstract val cityDao: CityDao

    companion object {
        @Volatile
        private var INSTANCE: CityBookmarkDatabase? = null

        fun getInstance(context: Context): CityBookmarkDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CityBookmarkDatabase::class.java,
                        "bookmarked_cities_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}