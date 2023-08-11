package com.space.moviesapp.domain.model


data class MoviesPageModel(
    val page: Int,
    val results: List<MovieItem>,
    val totalPages: Int
)