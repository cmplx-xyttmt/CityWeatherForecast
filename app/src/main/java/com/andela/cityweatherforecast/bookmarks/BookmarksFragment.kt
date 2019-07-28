package com.andela.cityweatherforecast.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding =
            DataBindingUtil.inflate<FragmentBookmarksBinding>(inflater, R.layout.fragment_bookmarks, container, false)

        val cityAdapter = CityAdapter()
        binding.bookmarkedCitiesList.adapter = cityAdapter

        // TODO: Add this grid layout manager and change the layout of the list item xml
//        val manager = GridLayoutManager(activity, 2)
//        binding.bookmarkedCitiesList.layoutManager = manager

        val bookmarksViewModel = ViewModelProviders.of(this).get(BookmarksViewModel::class.java)
        binding.lifecycleOwner = this

        bookmarksViewModel.bookMarkedCities.observe(viewLifecycleOwner, Observer {
            it?.let {
                cityAdapter.submitList(it)
            }
        })

        return binding.root
    }
}