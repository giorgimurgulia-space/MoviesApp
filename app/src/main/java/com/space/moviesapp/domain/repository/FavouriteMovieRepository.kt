package com.space.moviesapp.domain.repository

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieItemModel
import kotlinx.coroutines.flow.Flow

interface FavouriteMovieRepository {
    suspend fun getFavoriteMovie(limit: Int, offset: Int): Flow<PagingData<MovieItemModel>>

    suspend fun insertFavoriteMovie(movie: MovieItemModel)

    suspend fun deleteFavoriteMovie(id: Int)

    suspend fun isFavoriteMovie(id: Int): Boolean
}