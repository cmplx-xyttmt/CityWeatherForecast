package com.andela.cityweatherforecast.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andela.cityweatherforecast.data.City
import com.andela.cityweatherforecast.databinding.ListItemBookmarkedCityBinding

class CityAdapter(private val onClickListener: OnClickListener) : ListAdapter<City, CityAdapter.ViewHolder>(CityDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }


    class ViewHolder private constructor(private val binding: ListItemBookmarkedCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: City) {
            binding.city = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBookmarkedCityBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (city: City) -> Unit) {
        fun onClick(city: City) = clickListener(city)
    }
}

class CityDiffCallBack : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}