package com.space.movie.presentation.model

data class MovieItemUIModel(
    val id: Int,
    val genre: String,
    val title: String,
    val releaseDate: String,
    val backdropPoster: String,
    val mainPosterPath: String,
    var isFavourite: Boolean,
)


