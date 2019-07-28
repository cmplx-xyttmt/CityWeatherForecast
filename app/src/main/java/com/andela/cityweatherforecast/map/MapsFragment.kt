package com.andela.cityweatherforecast.map

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.bookmarks.ConfirmBookmarkDialog
import com.andela.cityweatherforecast.bookmarks.viewmodel.BookmarksViewModel
import com.andela.cityweatherforecast.bookmarks.viewmodel.BookmarksViewModelFactory
import com.andela.cityweatherforecast.data.City
import com.andela.cityweatherforecast.data.CityBookmarkDatabase
import com.andela.cityweatherforecast.databinding.FragmentMapsBinding
import com.andela.cityweatherforecast.util.MapMarker
import com.andela.cityweatherforecast.util.geoLocate

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment(), OnMapReadyCallback, ConfirmBookmarkDialog.ConfirmBookmarkDialogListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapsBinding
    private lateinit var bookmarksViewModel: BookmarksViewModel

    // TODO; Add markers to the bookmarks view model and differentiate between already bookmarked cities and those which are not.
    private val mapMarkers = mutableMapOf<String, MapMarker>()
    private lateinit var currMarkerId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CityBookmarkDatabase.getInstance(application).cityDao

        val viewModelFactory = BookmarksViewModelFactory(dataSource, application)
        bookmarksViewModel = ViewModelProviders.of(this, viewModelFactory).get(BookmarksViewModel::class.java)

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
            showConfirmBookmarkDialog(it.id)
        }
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            val cityClicked = geoLocate(latLng, this)
            val snippetText = cityClicked!!.cityCountryString()
            val marker = map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Click to bookmark city")
                    .snippet(snippetText)
                    .draggable(true)
            )
            mapMarkers[marker.id] = MapMarker(marker.id, cityClicked, marker.title, marker.snippet, false)
        }
    }

    private fun showConfirmBookmarkDialog(id: String) {
        val marker = mapMarkers[id]
        if (!marker!!.bookmarked) {
            currMarkerId = id
            val dialog = ConfirmBookmarkDialog()
            dialog.setTargetFragment(this, 0)
            val bundle = Bundle()
            bundle.putString("city_string", marker.snippet)
            dialog.arguments = bundle
            dialog.show(fragmentManager, "ConfirmBookmarkDialog")
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        bookmarksViewModel.onBookmarkConfirmed(mapMarkers[currMarkerId]!!.city)
        findNavController().navigate(MapsFragmentDirections.actionMapsFragmentToBookmarksFragment())
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.i("MapsFragment", "Cancel clicked")
        // Probably don't need this

    }
}
