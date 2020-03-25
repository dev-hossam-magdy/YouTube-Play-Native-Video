package com.example.youtubeplaynativevideo.ui.main.Videoslist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeplaynativevideo.R
import com.example.youtubeplaynativevideo.adapter.VideoListAdapter
import com.example.youtubeplaynativevideo.models.Channel
import com.example.youtubeplaynativevideo.network.models.VideoItem
import com.example.youtubeplaynativevideo.ui.videoplaying.VideoPlayingActivity
import com.example.youtubeplaynativevideo.util.Constants
import com.example.youtubeplaynativevideo.util.showOrHide
import kotlinx.android.synthetic.main.videos_list_fragment.*

class VideosListFragment : Fragment() {

    private lateinit var viewModel: VideosListViewModel
    private val TAG = "VideosListFragment"
    private lateinit var adapter :VideoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.videos_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VideosListViewModel::class.java)
        val item = arguments?.get(Constants.CHANNEL_KEY) as Channel
        Log.e(TAG,"this is the item ${item.title}")

        observeVideosList(item.id)
        initRecyclerView()
    }

    private fun observeVideosList(channelId:String) {
        viewModel.observeVideos(channelId).observe(viewLifecycleOwner, Observer {resource->
           when(resource.status){
               VideosListResource.ResurceStatus.LOADING->{
                   loding_progress_bar.showOrHide()
               }
               VideosListResource.ResurceStatus.ERROR->{
                   loding_progress_bar.showOrHide(true)
                   showError()
               }
               VideosListResource.ResurceStatus.SUCCESS->{
                   adapter.submitList(resource.data?.videoList)
                   loding_progress_bar.showOrHide(true)
               }
           }
        })
    }

    private fun initRecyclerView() {
        adapter = VideoListAdapter()
        adapter.onItemClickListener = object :VideoListAdapter.OnClickListner{
            override fun onItemClick(position: Int, item: VideoItem) {
                moveToVideoPlayingActivity(item.id?.videoId?:"undefind")
            }
        }
        videos_recycler_view.adapter = adapter
    }

    private fun moveToVideoPlayingActivity(videoId:String) {
        val intent = Intent(activity,VideoPlayingActivity::class.java)
        intent.putExtra(Constants.VIDEO_ID_KEY,videoId)
        startActivity(intent)
    }

    private fun showError() {
        Toast.makeText(activity?.applicationContext,getString(R.string.loadin_error),Toast.LENGTH_LONG)
            .show()
    }


}
