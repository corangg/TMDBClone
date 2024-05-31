package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdb.databinding.ItemSeeAllBinding
import com.example.tmdb.model.Result
import com.example.tmdb.util.ItemClickInterface
import java.sql.Date

class SeeAllAdapter(val list : MutableList<Result>, val onItemClickListener: ItemClickInterface) : RecyclerView.Adapter<SeeAllAdapter.SeeAllViewHolder>(){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder
    = SeeAllViewHolder(ItemSeeAllBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {
        holder.setPoster(list[position].poster_path)
        holder.setTitle(list[position].title)
        holder.setDate(list[position].release_date)
        holder.clickedItem(list[position].id)
    }

    fun addData(newItems: List<Result>) {
        val startPosition = list.size
        list.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    inner class SeeAllViewHolder(private val binding: ItemSeeAllBinding): RecyclerView.ViewHolder(binding.root){
        fun setPoster(url: String?){
            url?.let {
                val imageUrl ="https://image.tmdb.org/t/p/w500" + url
                Glide.with(binding.root).load(imageUrl).into(binding.seeAllImg)
            }
        }

        fun setTitle(title: String?){
            title?.let {
                binding.seeAllTitle.text = it
            }
        }

        fun setDate(date: String?){
            date?.let {
                binding.seeAllDate.text = it
            }
        }

        fun clickedItem(id: Int?){
            id?.let {
                binding.seeAllItem.setOnClickListener {
                    onItemClickListener.onMovieItemClick(id)
                }
            }
        }
    }
}