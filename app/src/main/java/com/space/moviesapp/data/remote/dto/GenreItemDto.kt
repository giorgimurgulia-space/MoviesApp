package com.space.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenreItemDto(
        val id: Int,
        @SerializedName("name")
        val title: String
    )