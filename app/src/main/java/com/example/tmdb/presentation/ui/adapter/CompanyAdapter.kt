package com.example.tmdb.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.model.detailmovie.ProductionCompany
import com.example.tmdb.databinding.ItemCompanyBinding
import com.example.tmdb.util.Util

class CompanyAdapter(val list: List<ProductionCompany>) :
    RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder =
        CompanyViewHolder(
            ItemCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.setLogo(list[position].logo_path)
        holder.setName(list[position].name)
    }

    inner class CompanyViewHolder(private val binding: ItemCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setLogo(url: String?) {
            url?.let {
                Util.setImage(it, binding.root, binding.imgCompany)
            }

        }

        fun setName(name: String?) {
            name?.let {
                binding.textCompany.text = it
            }
        }
    }
}