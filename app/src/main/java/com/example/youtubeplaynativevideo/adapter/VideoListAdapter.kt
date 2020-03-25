package com.example.youtubeplaynativevideo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeplaynativevideo.R
import com.example.youtubeplaynativevideo.models.Channel
import com.example.youtubeplaynativevideo.network.models.VideoItem
import kotlinx.android.synthetic.main.item_video.view.*

class VideoListAdapter:ListAdapter<VideoItem,VideoListAdapter.VideoListViewholder>(diffcallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video,parent,false)
        return VideoListViewholder(view)
    }

    override fun onBindViewHolder(holder: VideoListViewholder, position: Int) {
        val item = getItem(position)
        item?.let {video->
            Glide.with(holder.itemView.context)
                .load(video.snippet?.thumbnails?.medium?.url)
                .into(holder.videoImageView)
            holder.titleTextView.text = video.snippet?.title

            if(::onItemClickListener.isInitialized){
                holder.itemView.setOnClickListener {
                    onItemClickListener.onItemClick(position ,video)
                }
            }

        }
    }

    lateinit var onItemClickListener: OnClickListner

    interface OnClickListner{
        fun onItemClick(position:Int, item : VideoItem)
    }

    class VideoListViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var videoImageView: ImageView
        var titleTextView: TextView
        init {
            videoImageView = itemView.video_image_view
            titleTextView = itemView.title_text_view
        }
    }
    companion object{
        val diffcallBack =object : DiffUtil.ItemCallback<VideoItem>(){
            override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean = oldItem == newItem
        }
    }
}