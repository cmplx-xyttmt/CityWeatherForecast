package com.andela.cityweatherforecast.util

import android.location.Address
import android.location.Geocoder
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andela.cityweatherforecast.data.City
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

data class MapMarker(val id: String, val city: City, val title: String, val snippet: String, val bookmarked: Boolean)

fun createCityFromAddress(address: Address) = City(
    cityName = address.subAdminArea ?: address.adminArea,
    countryName = address.countryName,
    latitude = address.latitude,
    longitude = address.longitude
)

fun geoLocate(coordinates: LatLng, callingFragment: Fragment): City? {
    val geocoder = Geocoder(callingFragment.context)

    try {
        val address = geocoder.getFromLocation(coordinates.latitude, coordinates.longitude, 1)[0]
        return createCityFromAddress(address)
    } catch (ioException: IOException) {
        Toast.makeText(callingFragment.activity, "Cannot retrieve location data at the moment", Toast.LENGTH_LONG).show()
    }

    return null
}