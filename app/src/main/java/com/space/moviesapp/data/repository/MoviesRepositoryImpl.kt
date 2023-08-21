package com.space.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.moviesapp.common.resource.ApiError
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.paging.MoviesSearchPagingSource
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
        try {
            val response = apiService.getMoviesPage(categoryId, page)
            if (response.isSuccessful) {
                return moviesPageDtoToDomainMapper.invoke(
                    response.body()!!,
                    getMoviesGenres(),
                    moviesDao.getFavouriteMovies().map { it.id })
            } else {
                throw ApiError(Throwable())
            }
        } catch (e: CancellationException) {
            throw e
        }
//        return Pager(
//            config = PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20),
//            pagingSourceFactory = { MoviesPagingSource(apiService, categoryId) }
//        ).flow.map {
//            it.map { movie ->
//                movieItemDtoToDomainMapper.invoke(
//                    movie,
//                    getMoviesGenres(),
//                    moviesDao.isFavouriteMovie(movie.id ?: 0)
//                )
//            }
//        }
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

    override fun searchMovies(query: String): Flow<PagingData<MovieItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = { MoviesSearchPagingSource(apiService, query) }
        ).flow
            .map {
                it.map { movie ->
                    movieItemDtoToDomainMapper.invoke(
                        movie,
                        getMoviesGenres(),
                        moviesDao.isFavouriteMovie(movie.id ?: 0)
                    )
                }
            }
    }

    private val categoryList = listOf(
        MovieCategoryDto("popular", "Popular"),
        MovieCategoryDto("top_rated", "Top Rated")
    )
}