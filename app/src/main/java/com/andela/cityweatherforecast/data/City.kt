package com.andela.cityweatherforecast.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_cities_table")
data class City(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name = "country_name")
    val countryName: String,

    @ColumnInfo(name = "latitude")
    val latitude: Float,

    @ColumnInfo(name = "longitude")
    val longitude: Float
)