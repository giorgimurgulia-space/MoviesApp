package com.space.movie.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MoviesPageDto(
    val page: Int,
    val results: List<MovieItemDto>,
    @SerializedName("total_pages")
    val totalPages: Int
)