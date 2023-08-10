package com.space.moviesapp.domain.repository

import com.space.moviesapp.domain.model.MovieDetailsModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun invoke(movieId: Int): Flow<MovieDetailsModel>
}