package com.space.moviesapp.domain.repository


interface MoviesRepository {
    suspend fun getPopularMovies()

    suspend fun getTopMovies()

    suspend fun searchMovies()
}