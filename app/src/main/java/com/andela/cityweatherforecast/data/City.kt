package com.andela.cityweatherforecast.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "bookmarked_cities_table")
data class City(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name = "country_name")
    val countryName: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double
) : Parcelable {
    fun cityCountryString(): String {
        return "City: $cityName, Country: $countryName"
    }
}