package com.andela.cityweatherforecast.map

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentMapsBinding>(inflater, R.layout.fragment_maps, container, false)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        setMapLongClick(mMap)

        mMap.setOnInfoWindowClickListener {
            Toast.makeText(activity, "Clicked on bookmark", Toast.LENGTH_SHORT).show()
        }
    }

    private fun geoLocate(coordinates: LatLng): String? {
        val geocoder = Geocoder(this.context)

        try {
            val address = geocoder.getFromLocation(coordinates.latitude, coordinates.longitude, 1)[0]
            return "City: ${address.subAdminArea ?: address.adminArea}, Country: ${address.countryName}"
        } catch (ioException: IOException) {
            Toast.makeText(activity, "Cannot retrieve location data at the moment", Toast.LENGTH_LONG).show()
        }

        return null
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            val snippetText = geoLocate(latLng)
            if (snippetText != null) {
                map.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title("Click to bookmark city")
                        .snippet(snippetText)
                        .draggable(true)
                )
            }
        }
    }
}
