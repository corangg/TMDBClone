package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ItemMoviesBinding
import com.example.tmdb.databinding.ItemNowPlayingBinding
import com.example.tmdb.model.Result

class MoviesAdapter(val list : List<Result>, val onItemClickListener: OnMoviesItemClickListener)
    : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){
    interface OnMoviesItemClickListener{
        fun onMoviesItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder
            = MoviesViewHolder(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.setPoster(list[position].backdrop_path)
        holder.setTitle(list[position].title)
        holder.setPopularity(list[position].popularity)
    }
    inner class MoviesViewHolder(val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root){

        fun setPoster(url : String){
            val imageUrl ="https://image.tmdb.org/t/p/w500" + url

            Glide.with(binding.root).load(imageUrl).into(binding.imgMovies)
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

