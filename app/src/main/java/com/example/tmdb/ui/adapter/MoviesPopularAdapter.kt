package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ItemNowPlayingBinding
import com.example.tmdb.databinding.ItemPopularBinding
import com.example.tmdb.model.Result

class MoviesPopularAdapter(val list : List<Result>, val onItemClickListener: OnMoviesPopularItemClickListener)
    : RecyclerView.Adapter<MoviesPopularAdapter.MoviesPopularViewHolder>(){
    interface OnMoviesPopularItemClickListener{
        fun onMoviesPopularItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPopularViewHolder
            = MoviesPopularViewHolder(ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviesPopularViewHolder, position: Int) {
        holder.setPoster(list[position].backdrop_path)
        holder.setTitle(list[position].title)
        holder.setPopularity(list[position].popularity)
    }
    inner class MoviesPopularViewHolder(val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root){

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