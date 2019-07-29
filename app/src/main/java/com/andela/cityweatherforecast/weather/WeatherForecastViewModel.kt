package com.andela.cityweatherforecast.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andela.cityweatherforecast.data.City
import com.andela.cityweatherforecast.network.WeatherApi
import com.andela.cityweatherforecast.network.WeatherForecast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherForecastViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val couroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    var currentCity: City? = null
    private val _weatherForecastList = MutableLiveData<List<WeatherForecast>>()
    val weatherForecastList: LiveData<List<WeatherForecast>>
        get() = _weatherForecastList

    init {
        getWeatherForecastList()
    }

    private fun getWeatherForecastList() {
        couroutineScope.launch {
            val getFiveDayForecastDeferred =
                WeatherApi.retrofitService.getFiveDayForecastAsync(currentCity!!.latitude, currentCity!!.longitude)
            try {
                val result = getFiveDayForecastDeferred.await()
                _weatherForecastList.value = result.weatherForecasts
            } catch (e: Exception) {
                // TODO: Handle error
                _weatherForecastList.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}