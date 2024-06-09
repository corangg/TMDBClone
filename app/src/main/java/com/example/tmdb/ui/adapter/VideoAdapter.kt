package com.example.tmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.data.model.video.VideoResult
import com.example.tmdb.databinding.ItemVideoBinding
import com.example.tmdb.util.ItemClickInterface
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class VideoAdapter(val list: List<VideoResult>, val onVideoItemClickListener: ItemClickInterface) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder =
        VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.setTitle(list[position].name)
        holder.openVideo(list[position].key)
        holder.setVideo(list[position].key)
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setTitle(title: String?) {
            title?.let {
                binding.textTitle.text = title
            }
        }

        fun openVideo(key: String?) {
            key?.let {
                binding.view.setOnClickListener { view ->
                    onVideoItemClickListener.onVideoItemClick(it)
                }
            }
        }

        fun setVideo(key: String?) {
            key?.let {
                binding.youtubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(it, 0f)
                    }
                })
            }
        }
    }
}