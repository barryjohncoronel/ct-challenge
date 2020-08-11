package com.example.cartrack.ui.selectcountry.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cartrack.data.model.Country
import com.example.cartrack.databinding.ItemCountryBinding

class CountriesAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountriesAdapter.UserViewHolder>() {

    lateinit var itemListener: ItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemCountryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class UserViewHolder(
        private val itemCountryBinding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(itemCountryBinding.root) {

        fun bind(position: Int) {
            val userItemViewModel = CountryItemViewModel(countries[position])

            itemCountryBinding.viewModel = userItemViewModel

            itemCountryBinding.root.setOnClickListener {
                itemListener.onSelect(position)
            }

            itemCountryBinding.executePendingBindings()
        }
    }

    interface ItemListener {
        fun onSelect(selectedIndex: Int)
    }
}

