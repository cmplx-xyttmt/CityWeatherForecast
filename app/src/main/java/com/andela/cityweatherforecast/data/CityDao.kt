package com.andela.cityweatherforecast.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao {

    @Insert
    fun insert(city: City)

    @Update
    fun update(city: City)

    @Query("SELECT * FROM bookmarked_cities_table WHERE id = :key")
    fun get(key: Long): City

    @Query("SELECT * FROM bookmarked_cities_table ORDER BY id DESC")
    fun getAllCities(): LiveData<List<City>>

    @Delete
    fun delete(city: City)
}