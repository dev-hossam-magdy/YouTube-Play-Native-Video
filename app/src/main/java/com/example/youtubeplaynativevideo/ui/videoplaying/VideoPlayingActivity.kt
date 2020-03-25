package com.example.youtubeplaynativevideo.ui.videoplaying

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.youtubeplaynativevideo.R
import com.example.youtubeplaynativevideo.util.Constants
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_video_playing.*

class VideoPlayingActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var videoId:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_playing)
        videoId = intent.getStringExtra(Constants.VIDEO_ID_KEY)

        yotube_player_view.initialize(Constants.API_KEY,this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, mYouTubePlayer: YouTubePlayer?, p2: Boolean) {
        mYouTubePlayer?.loadVideo(videoId)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Toast.makeText(this,"Error: Check the internet conection",Toast.LENGTH_LONG).show()
    }
}
