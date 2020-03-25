package com.example.youtubeplaynativevideo.ui.main.Videoslist

import androidx.lifecycle.ViewModel

class VideosListViewModel : ViewModel() {

    private val repo = VideosListRepo

    fun observeVideos(channelId:String) = repo.getDataSource(channelId)
}
