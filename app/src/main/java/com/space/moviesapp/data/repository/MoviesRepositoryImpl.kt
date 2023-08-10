package com.space.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.moviesapp.common.maper.toDomainModel
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.paging.MoviesPagingSource
import com.space.moviesapp.data.paging.MoviesSearchPagingSource
import com.space.moviesapp.data.remote.api.ApiService
import com.space.moviesapp.data.remote.dto.MovieCategoryDto
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.CancellationException

class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override fun getMovieCategory(): Flow<List<MovieCategoryModel>> = flow {
        //todo list from Api
        emit(categoryList.mapIndexed { index, category ->
            category.toDomainModel(index)
        })
    }

    override suspend fun getMovies(categoryId: String): Flow<PagingData<MovieItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20),
            pagingSourceFactory = { MoviesPagingSource(apiService, categoryId) }
        ).flow.map {
            it.map { movie ->
                movie.toDomainModel(getMoviesGenres(), moviesDao.isFavouriteMovie(movie.id))
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

    override suspend fun searchMovies(query: String): Flow<PagingData<MovieItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = { MoviesSearchPagingSource(apiService, query) }
        ).flow
            .map {
                it.map { movie ->
                    movie.toDomainModel(getMoviesGenres(), moviesDao.isFavouriteMovie(movie.id))
                }
            }
    }

    private val categoryList = listOf(
        MovieCategoryDto("popular", "Popular"),
        MovieCategoryDto("top_rated", "Top Rated")
    )
}