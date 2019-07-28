package com.andela.cityweatherforecast.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andela.cityweatherforecast.data.City

class BookmarksViewModel : ViewModel() {

    private val _bookMarkedCities = MutableLiveData<List<City>>()
    val bookMarkedCities: LiveData<List<City>>
        get() = _bookMarkedCities

    init {
        _bookMarkedCities.value = listOf(
            City(1, "Kampala", "Uganda", 1.2f, 1.3121f),
            City(2, "Nairobi", "Kenya", 13.12f, 1.332121f),
            City(3, "Lagos", "Nigeria", 17.89f, 56.3121f),
            City(4, "Accra", "Ghana", 2.2f, 4.3116721f)
        )
    }
}