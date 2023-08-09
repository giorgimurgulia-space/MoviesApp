package com.space.moviesapp.common.extensions

import java.util.concurrent.TimeUnit

fun Int.fromMinutesToHHmm(): String {
    val hours = TimeUnit.MINUTES.toHours(this.toLong())
    val remainMinutes = this - TimeUnit.HOURS.toMinutes(hours)
    return String.format("%dh %dm", hours, remainMinutes)
}