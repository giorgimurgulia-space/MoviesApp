package com.space.moviesapp.domain.repository

import androidx.paging.PagingData
import com.space.moviesapp.data.local.database.entity.MovieEntity
import com.space.moviesapp.domain.model.MovieItemModel
import kotlinx.coroutines.flow.Flow

interface FavouriteMovieRepository {
    fun getFavoriteMovie(): Flow<PagingData<MovieItemModel>>

    suspend fun insertFavoriteMovie(movie: MovieEntity)

    suspend fun deleteFavoriteMovie(id: Int)

    suspend fun isFavoriteMovie(id: Int): Boolean
}