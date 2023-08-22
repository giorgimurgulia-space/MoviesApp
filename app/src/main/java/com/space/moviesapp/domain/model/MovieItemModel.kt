package com.space.moviesapp.domain.model

data class MovieItemModel(
    val id: Int,
    val genre: String,
    val title: String,
    val releaseDate: String,
    val backdropPosterPath: String,
    val mainPosterPath: String
)