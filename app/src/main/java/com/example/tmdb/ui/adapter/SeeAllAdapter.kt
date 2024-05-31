package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.model.Result
import com.example.tmdb.databinding.ItemMovieSeeAllBinding
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import java.sql.Date

class SeeAllAdapter(val list : MutableList<Result>, val onItemClickListener: ItemClickInterface) : RecyclerView.Adapter<SeeAllAdapter.SeeAllViewHolder>(){

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder
    = SeeAllViewHolder(ItemMovieSeeAllBinding.inflate(LayoutInflater.from(parent.context),parent,false))

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

    inner class SeeAllViewHolder(private val binding: ItemMovieSeeAllBinding): RecyclerView.ViewHolder(binding.root){
        fun setPoster(url: String?){
            url?.let {
                Util.setImage(it, binding.root, binding.imgPoster)
            }
        }

        fun setTitle(title: String?){
            title?.let {
                binding.textTitle.text = it
            }
        }

        fun setDate(date: String?){
            date?.let {
                binding.textDate.text = it
            }
        }

        fun clickedItem(id: Int?){
            id?.let {
                binding.itemMovieSeeAll.setOnClickListener {view->
                    onItemClickListener.onMovieItemClick(it)
                }
            }
        }
    }
}