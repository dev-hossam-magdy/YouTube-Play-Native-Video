package com.example.youtubeplaynativevideo.ui.main.Videoslist

sealed class VideosListResource<T>(val status:ResurceStatus ,val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : VideosListResource<T>(ResurceStatus.SUCCESS,data)
    class Loading<T>(data: T? = null) : VideosListResource<T>(ResurceStatus.LOADING,data)
    class Error<T>(message: String = "error", data: T? = null) : VideosListResource<T>(ResurceStatus.ERROR,data,message)

    enum class ResurceStatus {
         ERROR, LOADING, SUCCESS
    }

}
