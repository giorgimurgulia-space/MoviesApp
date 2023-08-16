package com.space.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieItemDto(
    val id: Int?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("backdrop_path")
    val backdropPosterPath: String?,
    @SerializedName("poster_path")
    val mainPosterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
)