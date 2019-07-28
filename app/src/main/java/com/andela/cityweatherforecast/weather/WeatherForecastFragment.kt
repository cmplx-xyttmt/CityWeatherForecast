package com.andela.cityweatherforecast.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.databinding.FragmentWeatherForecastBinding

class WeatherForecastFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentWeatherForecastBinding>(
            inflater,
            R.layout.fragment_weather_forecast,
            container,
            false
        )


        return binding.root
    }
}