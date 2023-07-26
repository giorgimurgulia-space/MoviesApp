package com.space.moviesapp.domain.repository

import com.space.moviesapp.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): Flow<List<MovieModel>>

    suspend fun getTopMovies(): Flow<List<MovieModel>>

    suspend fun searchMovies()
}