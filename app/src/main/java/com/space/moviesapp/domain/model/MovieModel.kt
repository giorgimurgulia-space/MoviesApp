package com.space.moviesapp.domain.model


data class MovieModel(
    val page: Int,
    val results: List<MovieItemModel>,
    val totalPages: Int
)