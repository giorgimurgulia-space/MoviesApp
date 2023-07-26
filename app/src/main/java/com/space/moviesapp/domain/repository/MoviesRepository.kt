package com.space.moviesapp.domain.repository

import com.space.moviesapp.domain.model.MovieCategoryModel
import com.space.moviesapp.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    fun getMovieCategory(): List<MovieCategoryModel>

    suspend fun getMovies(categoryId: String, page: Int): Flow<MovieModel>

    suspend fun searchMovies()
}