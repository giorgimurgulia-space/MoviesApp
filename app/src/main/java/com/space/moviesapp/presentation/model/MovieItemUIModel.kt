package com.space.moviesapp.presentation.model

data class MovieItemUIModel(
    val id: Int,
    val genres: String,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
    var isFavorite: Boolean
)