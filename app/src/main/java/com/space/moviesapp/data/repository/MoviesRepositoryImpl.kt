package com.space.moviesapp.data.repository

import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override fun getMovieCategory(): List<MovieCategoryModel> {
        return listOf(
            MovieCategoryModel("popular", "Popular"),
            MovieCategoryModel("top_rated", "Top Rated")
        )
    }

    override suspend fun getMovies(categoryId: String, page: Int): Flow<MovieModel> = flow {
        val genresResponse = apiService.getMovieGenres()
        val response = apiService.getPopularMovies(categoryId, page)
        if (response.isSuccessful && genresResponse.isSuccessful) {
            emit(response.body()!!.toDomainModel(genresResponse.body()!!.toDomainModel()))
        }
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }
}