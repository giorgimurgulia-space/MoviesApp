package com.space.moviesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieItemDto(
    val id: Int?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)