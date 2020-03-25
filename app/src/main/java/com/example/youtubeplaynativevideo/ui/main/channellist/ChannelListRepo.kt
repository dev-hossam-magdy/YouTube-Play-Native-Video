package com.example.youtubeplaynativevideo.ui.main.channellist

import androidx.lifecycle.MutableLiveData
import com.example.youtubeplaynativevideo.R
import com.example.youtubeplaynativevideo.models.Channel

object ChannelListRepo {

    private fun createResource():List<Channel>{
        val dataSource = mutableListOf<Channel>()
        dataSource.add(Channel("Acadmind", R.drawable.academind,"UCSJbGtTlrDami-tDGPUV9-w"))
        dataSource.add(Channel("Coding with Mitch", R.drawable.cwm,"UCoNZZLhPuuRteu02rh7bzsw"))
        dataSource.add(Channel("Coding in flow", R.drawable.cif,"UC_Fh8kvtkVPkeihBs42jGcA"))
        dataSource.add(Channel("Smartherd", R.drawable.sh,"UC0FPjuZLQ16UpvLtbs6LYpg"))
        dataSource.add(Channel("Android", R.drawable.android_logo,"UCVHFbqXqoYvEWM1Ddxl0QDg"))
        dataSource.add(Channel("JetBrains TV", R.drawable.jet_brains,"UCGp4UBwpTNegd_4nCpuBcow"))
        return dataSource
    }

    fun getDataSource (): MutableLiveData<List<Channel>> {
        val sources:MutableLiveData<List<Channel>> = MutableLiveData<List<Channel>>()
        sources.value = createResource()
        return  sources
    }
}