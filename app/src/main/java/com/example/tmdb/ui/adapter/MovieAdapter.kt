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
import com.example.tmdb.data.model.Result
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util

class MovieAdapter(val list: List<Result>, val type: Int, val onItemClickListener: ItemClickInterface)
    : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>(){
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
            else -> throw IllegalArgumentException("Invalid view type")
        }
        return  MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class MoviesViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: com.example.tmdb.data.model.Result, position: Int){
            when(binding){
                is ItemMoviesBinding ->{
                    setPoster(result.backdrop_path, binding.imgMovies)
                    setTitle(result.title, binding.textTitle)
                    setPopularity(result.popularity, binding.textPopularity)
                    clickedMovie(result.id, binding.itemMovie)
                }
                is ItemNowPlayingBinding ->{
                    setPoster(result.poster_path, binding.imgPoster)
                    setTitle(result.title, binding.textTitle)
                    setPopularity(result.popularity, binding.textPopularity)
                    clickedMovie(result.id, binding.itemMovie)
                }
                is ItemPopularBinding -> {
                    setPoster(result.backdrop_path, binding.imgPoster)
                    setTitle(result.title, binding.textTitle)
                    setPopularity(result.popularity, binding.textPopularity)
                    clickedMovie(result.id, binding.itemMovie)
                }
                is ItemTopRatedBinding -> {
                    setPoster(result.poster_path, binding.imgPoster)
                    setTitle(result.title, binding.textTitle)
                    setPopularity(result.popularity, binding.textPopularity)
                    clickedMovie(result.id, binding.itmeMovie)
                }
            }
        }

        private fun setPoster(url : String?, view : ImageView){
            url?.let {
                Util.setImage(it, binding.root, view)
            }
        }

        private fun setTitle(title: String?, view : TextView){
            title?.let {
                view.text = it
            }
        }

        private fun setPopularity(value: Double?, view : TextView){
            value?.let {
                val popularity = "Popularity " + it.toString()
                view.text = popularity
            }
        }

        private fun clickedMovie(id : Int?, view: View){
            id?.let {
                view.setOnClickListener {view->
                    onItemClickListener.onMovieItemClick(it)
                }
            }
        }
    }
}