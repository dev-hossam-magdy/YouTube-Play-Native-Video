package com.example.youtubeplaynativevideo.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeplaynativevideo.R
import com.example.youtubeplaynativevideo.models.Channel
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelListAdapter :androidx.recyclerview.widget.ListAdapter<Channel,ChannelListAdapter.ChannelListViewholder>(diffcallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelListViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_channel,parent,false)

        return ChannelListViewholder(view)
    }

    override fun onBindViewHolder(holder: ChannelListViewholder, position: Int) {
        val item = getItem(position)
        item?.let {channel ->
            holder.titleTextView.text = channel.title
            holder.logoImageView.setImageResource(channel.ImageUrl)

            if(::onItemClickListener.isInitialized){
                holder.itemView.setOnClickListener {
                    onItemClickListener.onItemClick(position ,channel)
                }
            }
            if (::onSupscripeClickListener.isInitialized){
                holder.subscripeTextView.setOnClickListener {
                    onSupscripeClickListener.onItemClick(position,channel)
                }

            }
        }


    }

    lateinit var onItemClickListener: OnClickListner
    lateinit var onSupscripeClickListener: OnClickListner

    interface OnClickListner{
         fun onItemClick(position:Int, item :Channel)
    }
    class ChannelListViewholder(itemView:View) :RecyclerView.ViewHolder(itemView){
        var logoImageView:ImageView
        var titleTextView:TextView
        var subscripeTextView:TextView
        init {
            logoImageView = itemView.logo_image_view
            titleTextView = itemView.title_text_view
            subscripeTextView = itemView.subscripe_text_view
        }
    }

    companion object{
        val diffcallBack =object :DiffUtil.ItemCallback<Channel>(){
            override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean = oldItem == newItem
        }
    }
}