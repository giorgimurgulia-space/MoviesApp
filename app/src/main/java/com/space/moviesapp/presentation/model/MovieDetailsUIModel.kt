package com.space.moviesapp.presentation.model

data class MovieDetailsUIModel(
    val id: Int = 0,
    val genre: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val backdropPoster: String = "",
    val mainPosterPath: String = "",
    val releaseDate: String = "",
    val runtime: Int = 0,
    val voteAverage: Double = 0.00,
    val isFavourite: Boolean = false
)