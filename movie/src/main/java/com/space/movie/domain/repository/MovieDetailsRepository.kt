package com.space.movie.domain.repository

import com.space.movie.domain.model.MovieDetailsModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun invoke(movieId: Int): Flow<MovieDetailsModel>
}