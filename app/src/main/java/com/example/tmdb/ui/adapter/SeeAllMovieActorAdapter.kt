package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.model.credit.Cast
import com.example.tmdb.databinding.ItemActorSeeAllBinding
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util

class SeeAllMovieActorAdapter(val list: MutableList<Cast>, val onItemClickListener: ItemClickInterface) : RecyclerView.Adapter<SeeAllMovieActorAdapter.SeeAllMovieActorViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllMovieActorViewHolder
            = SeeAllMovieActorViewHolder(ItemActorSeeAllBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: SeeAllMovieActorViewHolder, position: Int) {
        holder.setProfile(list[position].profile_path)
        holder.setName(list[position].name)
        holder.setPopular(list[position].popularity)
        holder.clickedItem(list[position].id)
        holder.setKnown(list[position].known_for_department)
    }

    fun addData(newItems: List<Cast>) {
        val startPosition = list.size
        list.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    inner class SeeAllMovieActorViewHolder(private val binding: ItemActorSeeAllBinding): RecyclerView.ViewHolder(binding.root){

        fun setProfile(url: String?){
            url?.let {
                Util.setImage(it, binding.root, binding.imgProfile)
            }
        }

        fun setName(title: String?){
            title?.let {
                binding.textName.text = it
            }
        }

        fun setKnown(known: String?){
            known?.let {
                binding.textJob.text = it
            }
        }

        fun setPopular(popular : Double?){
            popular?.let {
                val text = "Popularity $it"
                binding.textPopularity.text = text
            }
        }

        fun clickedItem(id: Int?){
            id?.let {
                binding.itemActorSeeAll.setOnClickListener {view->
                    onItemClickListener.onActorItemClick(it)
                }
            }
        }
    }
}