package com.space.moviesapp.domain.model

data class MovieModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    val poster: String,
)