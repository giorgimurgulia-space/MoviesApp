package com.space.moviesapp.presentation.model

data class MovieUIItem(
        val id: Int,
        val genres: List<String?>,
        val title: String,
        val rating: Double,
        val releaseDate: String,
        val poster: String
    )