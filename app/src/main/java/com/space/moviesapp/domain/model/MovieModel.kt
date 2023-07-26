package com.space.moviesapp.domain.model


data class MovieModel(
    val page: Int,
    val results: List<MovieItem>,
    val totalPages: Int
) {
    data class MovieItem(
        val id: Int,
        val title: String,
        val rating: Double,
        val releaseDate: String,
        val poster: String
    )
}