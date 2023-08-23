package com.space.movie.domain.repository

import com.space.movie.domain.model.MovieCategoryModel
import com.space.movie.domain.model.MoviesPageModel
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    fun getMovieCategory(): Flow<List<MovieCategoryModel>>

    suspend fun getMovies(categoryId: String, page: Int): MoviesPageModel

    suspend fun getMoviesGenres(): Map<Int, String>?

    suspend fun searchMovies(query: String, page: Int): MoviesPageModel
}