package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ItemCompanyBinding
import com.example.tmdb.model.detailmovie.ProductionCompany

class CompanyAdapter (val list: List<ProductionCompany>) : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>(){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder
            = CompanyViewHolder(ItemCompanyBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.setLogo(list[position].logo_path)
        holder.setName(list[position].name)
    }

    inner class CompanyViewHolder(private val binding: ItemCompanyBinding) : RecyclerView.ViewHolder(binding.root){
        fun setLogo(url: String?){
            url?.let {
                val imageUrl ="https://image.tmdb.org/t/p/w500" + it
                Glide.with(binding.root).load(imageUrl).into(binding.imgCompany)
            }

        }

        fun setName(name: String){
            binding.textCompany.text = name
        }
    }
}