package com.andela.cityweatherforecast.network

import com.squareup.moshi.Json

data class Response(
    @Json(name = "list")
    val weatherForecasts: List<WeatherForecast>
)

data class WeatherForecast(
    @Json(name = "dt")
    val date: Long,
    val temp: Temperature,
    val weather: Weather
)

data class Temperature(
    val min: Double,
    val max: Double
)

data class Weather(
    val main: String,
    val description: String
)