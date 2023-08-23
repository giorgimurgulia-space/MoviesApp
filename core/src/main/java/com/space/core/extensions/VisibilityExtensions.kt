package com.space.core.extensions

import android.view.View

fun View.changeVisibility() = run {
    visibility = if (visibility == View.GONE)
        View.VISIBLE
    else
        View.GONE
}

fun View.hide() = run {
    visibility = View.GONE
}

fun View.show() = run {
    visibility = View.VISIBLE
}
