package com.space.moviesapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MoviesDto(
    val page: Int,
    val results: List<MovieItemDto>,
    @SerializedName("total_pages")
    val totalPages: Int
)