package com.andela.cityweatherforecast.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.andela.cityweatherforecast.R
import com.andela.cityweatherforecast.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentBookmarksBinding>(inflater, R.layout.fragment_bookmarks, container, false)


        return binding.root
    }
}