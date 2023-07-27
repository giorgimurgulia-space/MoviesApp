package com.space.moviesapp.domain.model

data class MovieItemModel(
    val id: Int,
    val genres: String,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    var isFavorite: Boolean = false
)