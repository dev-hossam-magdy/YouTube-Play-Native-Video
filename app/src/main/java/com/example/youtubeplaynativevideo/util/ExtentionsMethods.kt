package com.example.youtubeplaynativevideo.util

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.showOrHide(isVisable:Boolean = false) {
    if (isVisable)
        this.visibility = View.GONE
    else
        this.visibility = View.VISIBLE

}