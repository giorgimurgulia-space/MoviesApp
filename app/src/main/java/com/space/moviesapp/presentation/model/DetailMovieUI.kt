package com.space.moviesapp.presentation.model

data class DetailMovieUI(
    val id: Int,
    val genres: List<String>,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double)