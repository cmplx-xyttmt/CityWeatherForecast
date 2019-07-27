package com.andela.cityweatherforecast.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeScreenBinding>(inflater, R.layout.fragment_home_screen, container, false)

        binding.viewMapButton.setOnClickListener {
            it.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToMapsFragment())
        }

        binding.viewBookmarksButton.setOnClickListener {
            it.findNavController().navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToBookmarksFragment())
        }

        return binding.root
    }
}