package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ItemMoviesBinding
import com.example.tmdb.databinding.ItemNowPlayingBinding
import com.example.tmdb.databinding.ItemPopularBinding
import com.example.tmdb.databinding.ItemTopRatedBinding
import com.example.tmdb.model.Result

class movieAdapter(val list: List<Result>, val type: Int, val onItemClickListener: OnItemClickListener )
    : RecyclerView.Adapter<movieAdapter.MoviesViewHolder>(){
    interface OnItemClickListener{
        fun onItemClick(id : Int)
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when(type){
            0->{
                ItemMoviesBinding.inflate(inflater, parent,false)
            }
            1->{
                ItemNowPlayingBinding.inflate(inflater, parent,false)
            }
            2->{
                ItemPopularBinding.inflate(inflater, parent, false)
            }
            3->{
                ItemTopRatedBinding.inflate(inflater, parent, false)
            }
            4->{
                ItemNowPlayingBinding.inflate(inflater, parent,false)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
        return  MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class MoviesViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result, position: Int){
            when(binding){
                is ItemMoviesBinding ->{
                    setPoster(result.backdrop_path, binding.imgMovies)
                    setTitle(result.title, binding.title)
                    setPopularity(result.popularity, binding.popularity)
                    clickedMovie(result.id, binding.movie)
                }
                is ItemNowPlayingBinding ->{
                    setPoster(result.poster_path, binding.poster)
                    setTitle(result.title, binding.title)
                    setPopularity(result.popularity, binding.popularity)
                    clickedMovie(result.id, binding.movie)
                }
                is ItemPopularBinding -> {
                    setPoster(result.backdrop_path, binding.poster)
                    setTitle(result.title, binding.title)
                    setPopularity(result.popularity, binding.popularity)
                    clickedMovie(result.id, binding.movie)
                }
                is ItemTopRatedBinding -> {
                    setPoster(result.backdrop_path, binding.poster)
                    setTitle(result.title, binding.title)
                    setPopularity(result.popularity, binding.popularity)
                    clickedMovie(result.id, binding.movie)
                }
            }
        }

        private fun setPoster(url : String, view : ImageView){
            val imageUrl ="https://image.tmdb.org/t/p/w500$url"

            Glide.with(binding.root).load(imageUrl).into(view)
        }

        private fun setTitle(title: String, view : TextView){
            view.text = title
        }

        private fun setPopularity(value: Double, view : TextView){
            val popularity = "Popularity " + value.toString()
            view.text = popularity
        }

        private fun clickedMovie(id : Int,view: View){
            view.setOnClickListener {
                onItemClickListener.onItemClick(id)
            }

        }
    }
}