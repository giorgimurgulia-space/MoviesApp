package com.space.moviesapp.domain.repository

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItemModel
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    fun getMovieCategory(): Flow<List<MovieCategoryModel>>

    fun getMovies(categoryId: String): Flow<PagingData<MovieItemModel>>

    suspend fun getMoviesGenres(): Map<Int, String>

    fun searchMovies(query: String): Flow<PagingData<MovieItemModel>>
}