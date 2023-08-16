package com.space.moviesapp.domain.model

data class MovieDetailsModel(
    val id: Int,
    val genre: String,
    val title: String,
    val overview: String,
    val backdropPoster: String,
    val mainPoster: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val isFavourite: Boolean
)