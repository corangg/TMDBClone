package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdb.databinding.ItemCelebritiesTrendingBinding
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util

class CelebritiesTrendingAdapter(val list: List<CelebritiesResult>, val onItemClickListener: ItemClickInterface): RecyclerView.Adapter<CelebritiesTrendingAdapter.CelebritiesTrendingViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CelebritiesTrendingViewHolder
    = CelebritiesTrendingViewHolder(ItemCelebritiesTrendingBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CelebritiesTrendingViewHolder, position: Int) {
        holder.setProfile(list[position].profile_path)
        holder.setName(list[position].name)
        holder.setPopular(list[position].popularity)
        holder.itemClicked(list[position].id)
    }

    inner class  CelebritiesTrendingViewHolder(private val binding : ItemCelebritiesTrendingBinding): RecyclerView.ViewHolder(binding.root){
        fun setProfile(url: String?){
            url?.let {
               Util.setImage(it, binding.root, binding.imgProfile)
            }
        }

        fun setName(name: String?){
            name?.let {
                binding.textName.text = it
            }
        }

        fun setPopular(popular : Double?){
            popular?.let {
                val text = "Popularity $popular"
                binding.textPopularity.text = text
            }
        }

        fun itemClicked(id: Int?){
            binding.itemTrending.setOnClickListener {
                id?.let {
                    onItemClickListener.onActorItemClick(it)
                }
            }
        }
    }
}