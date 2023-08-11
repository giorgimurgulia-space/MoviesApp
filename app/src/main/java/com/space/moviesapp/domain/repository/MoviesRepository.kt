package com.space.moviesapp.domain.repository

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    fun getMovieCategory(): Flow<List<MovieCategoryModel>>

    suspend fun getMovies(categoryId: String): Flow<PagingData<MovieItem>>

    suspend fun getMoviesGenres(): Map<Int, String>

    suspend fun searchMovies()
}