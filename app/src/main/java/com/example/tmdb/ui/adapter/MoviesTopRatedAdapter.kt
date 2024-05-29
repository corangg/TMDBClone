package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ItemTopRatedBinding
import com.example.tmdb.model.Result

class MoviesTopRatedAdapter (val list : List<Result>, val onItemClickListener: OnMoviesTopRatedItemClickListener)
    : RecyclerView.Adapter<MoviesTopRatedAdapter.MoviesTopRatedViewHolder>(){
    interface OnMoviesTopRatedItemClickListener{
        fun onMoviesTopRatedItemClick(position: Int)

        fun onMoviesTopRatedDetailItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesTopRatedViewHolder
            = MoviesTopRatedViewHolder(ItemTopRatedBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviesTopRatedViewHolder, position: Int) {
        holder.setPoster(list[position].poster_path)
        holder.setTitle(list[position].title)
        holder.setPopularity(list[position].popularity)
    }
    inner class MoviesTopRatedViewHolder(val binding: ItemTopRatedBinding) : RecyclerView.ViewHolder(binding.root){

        fun setPoster(url : String){
            val imageUrl ="https://image.tmdb.org/t/p/w500" + url

            Glide.with(binding.root).load(imageUrl).into(binding.poster)
        }

        fun setTitle(title: String){
            binding.title.text = title
        }

        fun setPopularity(value: Double){
            val popularity = "Popularity " + value.toString()
            binding.popularity.text = popularity
        }
    }
}