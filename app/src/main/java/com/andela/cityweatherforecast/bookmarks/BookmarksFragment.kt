package com.andela.cityweatherforecast.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.bookmarks.viewmodel.BookmarksViewModel
import com.andela.cityweatherforecast.bookmarks.viewmodel.BookmarksViewModelFactory
import com.andela.cityweatherforecast.data.CityBookmarkDatabase
import com.andela.cityweatherforecast.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding =
            DataBindingUtil.inflate<FragmentBookmarksBinding>(inflater, R.layout.fragment_bookmarks, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = CityBookmarkDatabase.getInstance(application).cityDao

        val viewModelFactory = BookmarksViewModelFactory(dataSource, application)
        val bookmarksViewModel = ViewModelProviders.of(this, viewModelFactory).get(BookmarksViewModel::class.java)

        binding.lifecycleOwner = this

        val cityAdapter = CityAdapter(CityAdapter.OnClickListener {
            bookmarksViewModel.displayWeatherForecastForCity(it)
        })

        binding.bookmarkedCitiesList.adapter = cityAdapter

        bookmarksViewModel.bookMarkedCities.observe(viewLifecycleOwner, Observer {
            it?.let {
                cityAdapter.submitList(it)
            }
        })

        bookmarksViewModel.navigateToSelectedCity.observe(this, Observer {
            it?.let {
                this.findNavController()
                    .navigate(BookmarksFragmentDirections.actionBookmarksFragmentToWeatherForecastFragment(it))
                bookmarksViewModel.displayWeatherForecastForCityComplete()
            }
        })

        return binding.root
    }
}