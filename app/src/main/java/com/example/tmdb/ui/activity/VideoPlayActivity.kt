package com.example.tmdb.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityVideoPlayBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val key = intent.getStringExtra("videoKey")
        key?.let {
            setVideo(it)
        }
    }

    private fun setVideo(key: String){
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(key,0f)
            }
        })
    }
}