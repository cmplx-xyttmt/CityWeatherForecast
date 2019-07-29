package com.andela.cityweatherforecast.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.data.City
import com.andela.cityweatherforecast.databinding.FragmentWeatherForecastBinding

class WeatherForecastFragment : Fragment() {

    private lateinit var binding: FragmentWeatherForecastBinding
    private lateinit var currentCity: City

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_forecast,
            container,
            false
        )
        currentCity = WeatherForecastFragmentArgs.fromBundle(arguments!!).city

        val viewModel = ViewModelProviders.of(this).get(WeatherForecastViewModel::class.java)
        viewModel.currentCity = currentCity

        val weatherAdapter = WeatherAdapter()
        binding.weatherForecastRecyclerView.adapter = weatherAdapter

        viewModel.weatherForecastList.observe(viewLifecycleOwner, Observer {
            it?.let {
                weatherAdapter.submitList(it)
            }
        })

        return binding.root
    }
}