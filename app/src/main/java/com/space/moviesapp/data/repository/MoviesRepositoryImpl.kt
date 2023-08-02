package com.space.moviesapp.data.repository

import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MoviesPageModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val apiService: ApiService
) : MoviesRepository {

    override fun getMovieCategory(): List<MovieCategoryModel> {
        return listOf(
            MovieCategoryModel("popular", "Popular"),
            MovieCategoryModel("top_rated", "Top Rated")
        )
    }

    override suspend fun getMovies(categoryId: String, page: Int): Flow<MoviesPageModel> = flow {
        val response = apiService.getMoviesPage(categoryId, page)


        if (response.isSuccessful) {
            emit(response.body()!!.toDomainModel(getGenres()))
        }
    }

    private suspend fun getGenres(): Map<Int, String> {
        val response = apiService.getMovieGenres()
        return if (response.isSuccessful) {
            response.body()!!.toDomainModel()
        } else {
            emptyMap<Int, String>()
        }
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }
}