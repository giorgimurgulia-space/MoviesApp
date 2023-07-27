package com.space.moviesapp.domain.repository

import com.space.moviesapp.domain.model.MovieItemModel

interface FavouriteMovieRepository {
    suspend fun getFavoriteMovie(): List<MovieItemModel>

    suspend fun insertFavoriteMovie(movie: MovieItemModel)

    suspend fun deleteFavoriteMovie(id: Int)

    suspend fun isFavoriteMovie(id: Int): Boolean
}