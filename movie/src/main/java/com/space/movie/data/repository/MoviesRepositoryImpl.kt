package com.space.movie.data.repository

import com.space.movie.data.remote.api.ApiService
import com.space.movie.data.remote.dto.MovieCategoryDto
import com.space.movie.data.remote.mapper.GenresDtoToDomainMapper
import com.space.movie.data.remote.mapper.MovieCategoryDtoToDomainMapper
import com.space.movie.data.remote.mapper.MoviePageDtoToDomainMapper
import com.space.movie.domain.model.MovieCategoryModel
import com.space.movie.domain.model.MoviesPageModel
import com.space.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException

class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val movieCategoryDtoToDomainMapper: MovieCategoryDtoToDomainMapper,
    private val movieGenresDtoToDomainMapper: GenresDtoToDomainMapper,
    private val moviesPageDtoToDomainMapper: MoviePageDtoToDomainMapper
) : MoviesRepository {

    override fun getMovieCategory(): Flow<List<MovieCategoryModel>> = flow {
        //todo list from Api
        emit(categoryList.mapIndexed { index, category ->
            movieCategoryDtoToDomainMapper.invoke(category, index)
        })
    }

    override suspend fun getMovies(categoryId: String, page: Int): MoviesPageModel {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMoviesPage(categoryId, page)
                if (response.isSuccessful) {
                    moviesPageDtoToDomainMapper.invoke(
                        response.body()!!,
                        getMoviesGenres()
                    )
                } else {
                    throw com.space.core.resource.ApiError(Throwable())
                }
            } catch (e: CancellationException) {
                throw e
            }
        }
    }

    override suspend fun searchMovies(query: String, page: Int): MoviesPageModel {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchMovies(query, page)
                if (response.isSuccessful) {
                    moviesPageDtoToDomainMapper.invoke(
                        response.body()!!,
                        getMoviesGenres()
                    )
                } else {
                    throw com.space.core.resource.ApiError(Throwable())
                }
            } catch (e: CancellationException) {
                throw e
            }
        }
    }

    override suspend fun getMoviesGenres(): Map<Int, String>? {
        try {
            val response = apiService.getMovieGenres()
            return if (response.isSuccessful) {
                movieGenresDtoToDomainMapper(response.body()!!)
            } else {
                null
            }
        } catch (e: CancellationException) {
            throw e
        }
    }

    private val categoryList = listOf(
        MovieCategoryDto("popular", "Popular"),
        MovieCategoryDto("top_rated", "Top Rated")
    )
}