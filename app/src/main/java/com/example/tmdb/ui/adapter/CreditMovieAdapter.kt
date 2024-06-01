package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.databinding.ItemNowPlayingBinding
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util

class CreditMovieAdapter(val list: List<ActorCast>, val onItemClickListener: ItemClickInterface): RecyclerView.Adapter<CreditMovieAdapter.CreditMovieViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditMovieViewHolder
    = CreditMovieViewHolder(ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CreditMovieViewHolder, position: Int) {
        holder.setPoster(list[position].poster_path)
        holder.setTitle(list[position].title)
        holder.setPopularity(list[position].popularity)
        holder.itemClicked(list[position].id)

    }

    inner class CreditMovieViewHolder(private val binding: ItemNowPlayingBinding) : RecyclerView.ViewHolder(binding.root){
        fun setPoster(url: String?){
            url?.let {
                Util.setImage(it, binding.root, binding.imgPoster) }
        }

        fun setTitle(name: String?){
            name?.let {
                binding.textTitle.text = it
            }
        }

        fun setPopularity(value: Double?){
            value?.let {
                val popularity = "Popularity " + it.toString()
                binding.textPopularity.text = popularity
            }
        }

        fun itemClicked(id: Int?){
            binding.itemMovie.setOnClickListener {
                id?.let {
                    onItemClickListener.onMovieItemClick(it)
                }
            }
        }
    }
}