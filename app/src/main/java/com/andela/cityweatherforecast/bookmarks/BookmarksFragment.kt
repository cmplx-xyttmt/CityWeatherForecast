package com.andela.cityweatherforecast.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.data.City
import com.andela.cityweatherforecast.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding =
            DataBindingUtil.inflate<FragmentBookmarksBinding>(inflater, R.layout.fragment_bookmarks, container, false)

        val cityAdapter = CityAdapter()
        binding.bookmarkedCitiesList.adapter = cityAdapter

        cityAdapter.submitList(
            listOf(
                City(1, "Kampala", "Uganda", 1.2f, 1.3121f),
                City(2, "Nairobi", "Kenya", 13.12f, 1.332121f),
                City(3, "Lagos", "Nigeria", 17.89f, 56.3121f),
                City(4, "Accra", "Ghana", 2.2f, 4.3116721f)
            )
        )
        return binding.root
    }
}