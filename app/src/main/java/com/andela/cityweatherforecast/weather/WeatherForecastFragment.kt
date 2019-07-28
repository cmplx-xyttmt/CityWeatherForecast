package com.andela.cityweatherforecast.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        viewModel.currentCity = WeatherForecastFragmentArgs.fromBundle(arguments!!).city
        viewModel.weatherForecastList.observe(this, Observer {
            it?.let {
                Log.i("WeatherForecastFragment", "The list is ready: It is of size: ${it.size} $it")
            }
        })
        return binding.root
    }
}