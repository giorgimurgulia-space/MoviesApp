package com.space.moviesapp.presentation.model

data class MoviesPageUIModel(
    val page: Int,
    val results: List<MovieItemUIModel>,
    val totalPages: Int
)