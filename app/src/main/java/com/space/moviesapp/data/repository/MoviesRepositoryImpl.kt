package com.space.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.presentation.ui.search.paging.MoviesSearchPagingSource
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieCategoryDto
import com.space.moviesapp.data.remote.mapper.GenresDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MovieCategoryDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MovieItemDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MoviePageDtoToDomainMapper
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.model.MoviesPageModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException

class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val movieCategoryDtoToDomainMapper: MovieCategoryDtoToDomainMapper,
    private val movieItemDtoToDomainMapper: MovieItemDtoToDomainMapper,
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
                        getMoviesGenres(),
                        moviesDao.getFavouriteMovies().map { it.id })
                } else {
                    throw ApiError(Throwable())
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
                        getMoviesGenres(),
                        moviesDao.getFavouriteMovies().map { it.id })
                } else {
                    throw ApiError(Throwable())
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