package com.andela.cityweatherforecast.bookmarks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.andela.cityweatherforecast.data.City
import com.andela.cityweatherforecast.data.CityDao
import kotlinx.coroutines.*

class BookmarksViewModel(private val database: CityDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val bookMarkedCities = database.getAllCities()

    fun onBookmarkConfirmed(city: City) {
        uiScope.launch {
            insert(city)
        }
    }

    private suspend fun insert(city: City) {
        withContext(Dispatchers.IO) {
            database.insert(city)
        }
    }
}