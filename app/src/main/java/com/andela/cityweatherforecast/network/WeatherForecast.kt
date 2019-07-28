package com.andela.cityweatherforecast.network

import com.squareup.moshi.Json

data class Response(
    @Json(name = "list")
    val weatherForecasts: List<WeatherForecast>
)

data class WeatherForecast(
    @Json(name = "dt")
    val date: Long,
    @Json(name = "main")
    val temp: Temperature,
    val weather: List<Weather>
)

data class Temperature(
    @Json(name = "temp_min")
    val min: Double,
    @Json(name = "temp_max")
    val max: Double
)

data class Weather(
    val main: String,
    val description: String
)