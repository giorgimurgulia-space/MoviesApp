package com.space.movie.presentation.model

data class MoviesPageUIModel(
    val page: Int,
    val results: List<MovieItemUIModel>,
    val totalPages: Int
)