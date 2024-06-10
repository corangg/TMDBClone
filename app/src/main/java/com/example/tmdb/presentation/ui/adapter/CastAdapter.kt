package com.example.tmdb.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.model.credit.Cast
import com.example.tmdb.databinding.ItemCastBinding
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util

class CastAdapter(val list: List<Cast>, val onItemClickListener: ItemClickInterface) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.setProfile(list[position].profile_path)
        holder.setName(list[position].name)
        holder.setCast(list[position].character)
        holder.itemClicked(list[position].id)
    }

    inner class CastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setProfile(url: String?) {
            url?.let {
                Util.setImage(it, binding.root, binding.imgProfile)
            }
        }

        fun setName(name: String?) {
            name?.let {
                binding.textName.text = it
            }
        }

        fun setCast(cast: String?) {
            cast?.let {
                binding.textCast.text = it
            }
        }

        fun itemClicked(id: Int?) {
            binding.itemCast.setOnClickListener {
                id?.let {
                    onItemClickListener.onActorItemClick(it)
                }
            }
        }
    }
}