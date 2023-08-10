package com.space.moviesapp.presentation.model

data class MovieItemUIModel(
    val id: Int,
    val genres: List<String?>,
    val title: String,
    val releaseDate: String,
    val poster: String,
    val isFavourite: Boolean,
)

