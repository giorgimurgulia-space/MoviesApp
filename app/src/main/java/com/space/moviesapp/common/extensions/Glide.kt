package com.space.moviesapp.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.space.moviesapp.R

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_no_poster)
        .error(R.drawable.ic_no_poster)
        .into(this)
}