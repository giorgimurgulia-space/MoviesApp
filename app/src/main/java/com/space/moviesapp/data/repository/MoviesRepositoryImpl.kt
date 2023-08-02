package com.space.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieCategoryDto
import com.space.moviesapp.data.remote.dto.MovieItemDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItem
import com.space.moviesapp.domain.repository.MoviesRepository
import com.space.moviesapp.presentation.ui.home.adapter.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val apiService: ApiService
) : MoviesRepository {

    override fun getMovieCategory(): Flow<List<MovieCategoryModel>> = flow {
        //todo list from Api
        emit(categoryList.mapIndexed { index, category ->
            category.toDomainModel(index)
        })
    }

    override suspend fun getMovies(categoryId: String): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(apiService, categoryId) }
        ).flow
            .map {
                it.map { movie ->
                    movie.toDomainModel(getMoviesGenres())
                }
            }
    }

    override suspend fun getMoviesGenres(): Map<Int, String> {
        val response = apiService.getMovieGenres()
        return if (response.isSuccessful) {
            response.body()!!.toDomainModel()
        } else {
            throw ApiError(Throwable())
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