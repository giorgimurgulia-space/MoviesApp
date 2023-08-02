package com.space.moviesapp.common.extensions

import android.view.View

fun View.changeVisibility() = run {
    visibility = if (visibility == View.GONE)
        View.VISIBLE
    else
        View.GONE
}
