package com.space.moviesapp.data.repository

import androidx.paging.*
import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieCategoryDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItem
import com.space.moviesapp.domain.repository.MoviesRepository
import com.space.moviesapp.data.paging.MoviesPagingSource
import com.space.moviesapp.data.paging.MoviesSearchPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.CancellationException
import kotlin.coroutines.coroutineContext

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
        ).flow.map {
            it.map { movie ->
                movie.toDomainModel(getMoviesGenres())
            }
        }
    }

    override suspend fun getMoviesGenres(): Map<Int, String> {
        val response = apiService.getMovieGenres()
        return if (response.isSuccessful) {
            try {
                response.body()!!.toDomainModel()
            } catch (e: CancellationException) {
                throw e
            }
        } else {
            emptyMap()
        }
    }

    override suspend fun searchMovies(query: String): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = { MoviesSearchPagingSource(apiService, query) }
        ).flow
            .map {
                it.map { movie ->
                    movie.toDomainModel(getMoviesGenres())
                }
            }
    }

    private val categoryList = listOf(
        MovieCategoryDto("popular", "Popular"),
        MovieCategoryDto("top_rated", "Top Rated")
    )
}