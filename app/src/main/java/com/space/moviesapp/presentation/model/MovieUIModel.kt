package com.space.moviesapp.presentation.model


data class MovieUIModel(
    val page: Int,
    val results: List<MovieUIItem>,
    val totalPages: Int
)