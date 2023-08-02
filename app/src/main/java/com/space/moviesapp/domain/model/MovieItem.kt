package com.space.moviesapp.domain.model

data class MovieItem(
        val id: Int,
        val genres: List<Int>,
        val title: String,
        val rating: Double,
        val releaseDate: String,
        val poster: String
    )