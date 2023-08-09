package com.space.moviesapp.presentation.model

<<<<<<<< HEAD:app/src/main/java/com/space/moviesapp/presentation/model/MovieItemUIModel.kt
data class MovieItemUIModel(
        val id: Int,
        val genres: List<String?>,
        val title: String,
        val rating: Double,
        val releaseDate: String,
        val poster: String
    )
========

data class MovieUIModel(
    val page: Int,
    val results: List<MovieItemUIModel>,
    val totalPages: Int
)
>>>>>>>> feature/details_screen/CMOB-1997:app/src/main/java/com/space/moviesapp/presentation/model/MovieUIModel.kt
