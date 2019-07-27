package com.andela.cityweatherforecast.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.data.City

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