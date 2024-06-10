package com.example.tmdb.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.databinding.ItemCountryBinding

class CountryAdapter(val list: List<String>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.setCountry(list[position])
    }

    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setCountry(string: String?) {
            string?.let {
                binding.textCountry.text = string
            }
        }
    }
}