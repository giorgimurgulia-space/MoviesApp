package com.space.movie.domain.model

data class MoviesPageModel(
    val page: Int,
    val results: List<MovieItemModel>,
    val totalPages: Int
)