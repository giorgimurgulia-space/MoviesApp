package com.space.moviesapp.data.repository

import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val apiService: ApiService

) : MoviesRepository {
    override suspend fun getPopularMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getTopMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }
}