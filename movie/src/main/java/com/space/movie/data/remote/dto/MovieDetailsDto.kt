package com.space.movie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    val genres: List<GenreItemDto>?,
    val id: Int?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    @SerializedName("backdrop_path")
    val backdropPosterPath: String?,
    @SerializedName("poster_path")
    val mainPosterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val runtime: Int?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)