package com.space.moviesapp.domain.repository

import com.space.moviesapp.domain.model.MovieItemModel
import kotlinx.coroutines.flow.Flow

interface FavouriteMovieRepository {
    suspend fun getFavoriteMovie(): Flow<List<MovieItemModel>>

    suspend fun insertFavoriteMovie(movie: MovieItemModel)

    suspend fun deleteFavoriteMovie(id: Int)

    suspend fun isFavoriteMovie(id: Int): Boolean
}