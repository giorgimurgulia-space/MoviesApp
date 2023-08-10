package com.space.moviesapp.domain.model

data class MovieItemModel(
    val id: Int,
    val genres: List<String?>,
    val title: String,
    val releaseDate: String,
    val poster: String,
    val isFavourite: Boolean
)