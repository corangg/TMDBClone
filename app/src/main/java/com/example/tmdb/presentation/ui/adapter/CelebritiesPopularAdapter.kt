package com.example.tmdb.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.databinding.ItemCelebritiesPopularBinding
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util

class CelebritiesPopularAdapter(
    val list: List<CelebritiesResult>,
    val onItemClickListener: ItemClickInterface
) : RecyclerView.Adapter<CelebritiesPopularAdapter.CelebritiesPopularViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CelebritiesPopularViewHolder = CelebritiesPopularViewHolder(
        ItemCelebritiesPopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: CelebritiesPopularViewHolder, position: Int) {
        holder.setProfile(list[position].profile_path)
        holder.setName(list[position].name)
        holder.setPopular(list[position].popularity)
        holder.itemClicked(list[position].id)
    }

    inner class CelebritiesPopularViewHolder(private val binding: ItemCelebritiesPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setProfile(url: String?) {
            url?.let {
                Util.setImage(it, binding.root, binding.imgProfile)
            }
        }

        fun setName(name: String?) {
            name?.let {
                binding.textName.text = name
            }
        }

        fun setPopular(popular: Double?) {
            popular?.let {
                val text = "Popularity $it"
                binding.textPopularity.text = text
            }
        }

        fun itemClicked(id: Int?) {
            binding.itemPopular.setOnClickListener {
                id?.let {
                    onItemClickListener.onActorItemClick(it)
                }
            }
        }
    }
}