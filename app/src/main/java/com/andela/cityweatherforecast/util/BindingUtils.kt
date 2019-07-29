package com.andela.cityweatherforecast.util

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.data.City
import com.andela.cityweatherforecast.network.WeatherForecast
import com.andela.cityweatherforecast.weather.WeatherAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@BindingAdapter("latitudeText")
fun TextView.setLatitudeText(item: City?) {
    item?.let {
        text = context.resources.getString(R.string.latitude_text, item.latitude)
    }
}

@BindingAdapter("longitudeText")
fun TextView.setLongitudeText(item: City?) {
    item?.let {
        text = context.resources.getString(R.string.longitude_text, item.longitude)
    }
}

@BindingAdapter("dayText")
fun bindDayText(textView: TextView, item: WeatherForecast?) {
    item?.let {
        val dateFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
        val date = dateFormatter.parse(item.date)
        textView.text = SimpleDateFormat("EEE, h:mm a", Locale.ENGLISH).format(date)
    }
}

@BindingAdapter("weatherDescriptionText")
fun bindWeatherDescriptionText(textView: TextView, item: WeatherForecast?) {
    item?.let {
        // TODO: Use string resource with placeholders
        textView.text = "${item.weather[0].description}"
    }
}

@BindingAdapter("weatherIcon")
fun bindWeatherIcon(imageView: ImageView, item: WeatherForecast?) {
    item?.let {
        val iconUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"
        Glide.with(imageView.context)
            .load(iconUrl)
            .into(imageView)
    }
}

@BindingAdapter("minTemperatureText")
fun TextView.setMinTemperatureText(item: WeatherForecast?) {
    item?.let {
        text = context.resources.getString(R.string.min_weather_display, item.temp.min)
    }
}

@BindingAdapter("maxTemperatureText")
fun TextView.setMaxTemperatureText(item: WeatherForecast?) {
    item?.let {
        text = context.resources.getString(R.string.max_weather_display, item.temp.max)
    }
}
