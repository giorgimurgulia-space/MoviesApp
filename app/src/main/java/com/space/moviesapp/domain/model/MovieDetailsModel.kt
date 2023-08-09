package com.space.moviesapp.domain.model

data class MovieDetailsModel(
    val id: Int,
    val genres: List<String>,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double
)