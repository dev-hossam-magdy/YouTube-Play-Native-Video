package com.example.youtubeplaynativevideo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Channel(
    val title:String,
    val ImageUrl:Int,
    val id:String
): Parcelable