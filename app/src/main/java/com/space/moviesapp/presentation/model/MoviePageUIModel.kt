package com.space.moviesapp.presentation.model


data class MoviePageUIModel(
    val page: Int,
    val results: List<MovieItemUIModel>,
    val totalPages: Int
)