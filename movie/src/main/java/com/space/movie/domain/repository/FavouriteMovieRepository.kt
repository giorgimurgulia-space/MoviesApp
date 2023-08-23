package com.space.movie.domain.repository

import com.space.movie.data.local.database.entity.MovieEntity
import com.space.movie.domain.model.MovieItemModel
import kotlinx.coroutines.flow.Flow

interface FavouriteMovieRepository {
    fun getFavoriteMovie(): Flow<List<MovieItemModel>>

    suspend fun insertFavoriteMovie(movie: MovieEntity)

    suspend fun deleteFavoriteMovie(id: Int)

    suspend fun isFavoriteMovie(id: Int): Boolean
}