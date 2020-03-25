package com.example.youtubeplaynativevideo.ui.main.Videoslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.youtubeplaynativevideo.network.ApiManger
import com.example.youtubeplaynativevideo.network.api.YouTubeApi
import com.example.youtubeplaynativevideo.network.models.ChannelVideosListResponse
import io.reactivex.schedulers.Schedulers

object VideosListRepo {
    val TAG ="VideosListRepo"
    private var api:YouTubeApi
    private lateinit var videosList:MediatorLiveData<VideosListResource<out ChannelVideosListResponse>>
    init {
        api = ApiManger.getInstance()
    }

    fun getDataSource (channelId:String): MediatorLiveData<VideosListResource<out ChannelVideosListResponse>> {
        videosList = MediatorLiveData<VideosListResource<out ChannelVideosListResponse>>()
        videosList.value = VideosListResource.Loading()

        val sources:LiveData<VideosListResource<out ChannelVideosListResponse>> = fetchDataFromApi(channelId)
        videosList.addSource(sources,object :Observer<VideosListResource<out ChannelVideosListResponse>>{
            override fun onChanged(resource: VideosListResource<out ChannelVideosListResponse>) {
                videosList.value = resource
                videosList.removeSource(sources)
            }
        })
        return videosList
    }

    private fun fetchDataFromApi(channelId:String):LiveData<VideosListResource<out ChannelVideosListResponse>> {
        val source : LiveData<VideosListResource<out ChannelVideosListResponse>> = LiveDataReactiveStreams.fromPublisher(
            api.getChannelVideosList(channelId)
                .subscribeOn(Schedulers.io())
                .onErrorReturn{ err->
                    Log.e(TAG,"Network error ${err.message}")
                    val invalidList  = ChannelVideosListResponse()
                    invalidList.kind = "error"
                    return@onErrorReturn invalidList
                }
                .map {res->
                    Log.e(TAG,"in map")

                    if (res.kind.equals("error")) {
                        Log.e(TAG,"in null")

                        return@map VideosListResource.Error("error", null)
                    }

                    Log.e(TAG,"good jop")
                    return@map VideosListResource.Success(res)

                }
        )

        return source
    }

}