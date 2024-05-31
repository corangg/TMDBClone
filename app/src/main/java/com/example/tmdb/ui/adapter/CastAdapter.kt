package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ActionMenuView.OnMenuItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdb.databinding.ItemCastBinding
import com.example.tmdb.databinding.ItemCompanyBinding
import com.example.tmdb.model.credit.Cast
import com.example.tmdb.model.detailmovie.ProductionCompany

class CastAdapter (val list: List<Cast>, val onActerItemClickListener: OnActerItemClickListener) : RecyclerView.Adapter<CastAdapter.CastViewHolder>(){

    interface OnActerItemClickListener{
        fun onActerClick(id : Int){

        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder
            = CastViewHolder(ItemCastBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.setProfile(list[position].profile_path)
        holder.setName(list[position].name)
        holder.setCast(list[position].character)
    }

    inner class CastViewHolder(private val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root){
        fun setProfile(url: String?){
            url?.let {
                val imageUrl ="https://image.tmdb.org/t/p/w500" + url
                Glide.with(binding.root).load(imageUrl).apply(RequestOptions().override(100,100)).into(binding.imgProfile)
            }
        }

        fun setName(name: String){
            binding.name.text = name
        }

        fun setCast(cast : String){
            binding.cast.text = cast
        }
    }
}