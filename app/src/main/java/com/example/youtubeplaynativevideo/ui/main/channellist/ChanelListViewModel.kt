package com.example.youtubeplaynativevideo.ui.main.channellist

import androidx.lifecycle.ViewModel

class ChanelListViewModel : ViewModel() {

    fun observeChannelList() =ChannelListRepo.getDataSource()
}
