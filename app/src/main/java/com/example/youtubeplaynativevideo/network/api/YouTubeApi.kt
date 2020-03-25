package com.example.youtubeplaynativevideo.network.api

import com.example.youtubeplaynativevideo.network.models.ChannelVideosListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("search")
    fun getChannelVideosList(
        @Query("channelId")channelId:String,
        @Query("part") part:String = "snippet,id",
        @Query("order") order:String ="date",
        @Query("maxResults") maxResults:Int =25
    ):Flowable<ChannelVideosListResponse>
}