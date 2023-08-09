package com.space.moviesapp.presentation.model

data class MovieDetailsUIModel(
    val id: Int? = null,
    val genres: List<String> = emptyList(),
    val originalTitle: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val runtime: Int? = null,
    val voteAverage: Double = 0.00
)