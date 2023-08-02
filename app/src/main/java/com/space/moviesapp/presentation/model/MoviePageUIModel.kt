package com.space.moviesapp.presentation.model


data class MoviePageUIModel(
    val page: Int,
    val results: List<MovieUIItem>,
    val totalPages: Int
)