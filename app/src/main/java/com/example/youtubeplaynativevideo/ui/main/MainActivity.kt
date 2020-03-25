package com.example.youtubeplaynativevideo.ui.main

import android.os.Bundle
import com.example.youtubeplaynativevideo.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : YouTubeBaseActivity() {

    lateinit var listener:YouTubePlayer.OnInitializedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listener = object :YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.let {
                    it.loadVideo("EgvawnVvSm4")
                }

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }
        }

        play_btn.setOnClickListener {
            my_youtube_player_view.initialize("AIzaSyB43vfEpUD3NFH5ZV3l1UXcJOZ5aZ7SIZQ",listener)
        }
    }
}
