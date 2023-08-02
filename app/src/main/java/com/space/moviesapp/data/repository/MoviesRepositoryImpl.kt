package com.space.moviesapp.data.repository

import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieCategoryDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MoviesPageModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val apiService: ApiService
) : MoviesRepository {

    override fun getMovieCategory(): Flow<List<MovieCategoryModel>> = flow {
        //todo list from Api
        emit(categoryList.mapIndexed { index, category ->
            category.toDomainModel(index)
        })
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
            emptyMap()
        }
    }

    override suspend fun searchMovies() {
        TODO("Search Branch")
    }

    private val categoryList = listOf(
        MovieCategoryDto("popular", "Popular"),
        MovieCategoryDto("top_rated", "Top Rated")
    )
}