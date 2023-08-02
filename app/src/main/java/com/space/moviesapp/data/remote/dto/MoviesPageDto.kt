package com.space.moviesapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MoviesPageDto(
    val page: Int,
    val results: List<MovieItem>,
    @SerializedName("total_pages")
    val totalPages: Int
)