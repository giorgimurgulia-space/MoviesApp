package com.space.moviesapp.data.repository

import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.domain.model.MovieModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val apiService: ApiService
) : MoviesRepository {


    override suspend fun getPopularMovies(page: Int): Flow<List<MovieModel>> = flow {
        val response = apiService.getPopularMovies(page)
        if (response.isSuccessful) {
            emit(response.body()!!.results.map {
                it!!.toDomainModel()
            })
        }
    }

    override suspend fun getTopMovies(page: Int): Flow<List<MovieModel>> = flow {
        val response = apiService.getTopMovies(page)
        if (response.isSuccessful) {
            emit(response.body()!!.results.map {
                it!!.toDomainModel()
            })
        }
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }
}