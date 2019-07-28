package com.andela.cityweatherforecast.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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

        val viewModel = ViewModelProviders.of(this).get(WeatherForecastViewModel::class.java)
        viewModel.weatherForecastList
        return binding.root
    }
}