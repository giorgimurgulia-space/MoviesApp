package com.space.moviesapp.presentation.model


data class MovieUIModel(
    val page: Int,
    val results: List<MovieItemUIModel>,
    val totalPages: Int
)