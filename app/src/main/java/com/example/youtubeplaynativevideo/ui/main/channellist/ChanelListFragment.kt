package com.example.youtubeplaynativevideo.ui.main.channellist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.youtubeplaynativevideo.R
import com.example.youtubeplaynativevideo.adapter.ChannelListAdapter
import com.example.youtubeplaynativevideo.models.Channel
import com.example.youtubeplaynativevideo.util.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.chanel_list_fragment.*

class ChanelListFragment : Fragment() {

    private val TAG = "ChanelListFragment"
    lateinit var adapter: ChannelListAdapter
    lateinit var navController: NavController


    private lateinit var viewModel: ChanelListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chanel_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        navController = Navigation.findNavController(view)
    }

    private fun initRecyclerView() {
        adapter = ChannelListAdapter()
        initOnItemClickListener()
        initOnSupscripeClickListener()


        recycler_view.adapter = adapter
    }

    private fun initOnSupscripeClickListener() {
        adapter.onSupscripeClickListener =object :ChannelListAdapter.OnClickListner{
            override fun onItemClick(position: Int, item: Channel) {
                Snackbar.make(channel_list_fragment_container,"You are Subscriped ${item.title} Channel",Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun initOnItemClickListener() {
        adapter.onItemClickListener = object :ChannelListAdapter.OnClickListner{
            override fun onItemClick(position: Int, item: Channel) {
//                if ()
                Log.e(TAG,item.title)
                navController.navigate(
                    R.id.action_chanelListFragment_to_videosListFragment,
                bundleOf(Constants.CHANNEL_KEY to item))
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(ChanelListViewModel::class.java)
        viewModel.observeChannelList().observe(viewLifecycleOwner, Observer { dataSource->
            adapter.submitList(dataSource)
        })
    }

}
