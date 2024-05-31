package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.databinding.ItemGenreBinding
import com.example.tmdb.model.detailmovie.Genre

class GenreAdapter (val list: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GemreViewHolder>(){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GemreViewHolder
            = GemreViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: GemreViewHolder, position: Int) {
        holder.setGenre(list[position].name)
    }

    inner class GemreViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root){
        fun setGenre(string: String){
            binding.itemGenre.text = string
        }
    }
}