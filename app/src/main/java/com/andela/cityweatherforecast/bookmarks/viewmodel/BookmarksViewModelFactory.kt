package com.andela.cityweatherforecast.bookmarks.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andela.cityweatherforecast.data.CityDao
import java.lang.IllegalArgumentException

class BookmarksViewModelFactory(
    private val dataSource: CityDao,
    private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarksViewModel::class.java)) {
            return BookmarksViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}