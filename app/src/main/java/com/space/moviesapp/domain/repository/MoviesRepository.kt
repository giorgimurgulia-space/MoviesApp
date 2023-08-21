package com.space.moviesapp.domain.repository

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.model.MoviesPageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface MoviesRepository {
    fun getMovieCategory(): Flow<List<MovieCategoryModel>>

    suspend fun getMovies(categoryId: String, page: Int): MoviesPageModel

    suspend fun getMoviesGenres(): Map<Int, String>?

    fun searchMovies(query: String): Flow<PagingData<MovieItemModel>>
}