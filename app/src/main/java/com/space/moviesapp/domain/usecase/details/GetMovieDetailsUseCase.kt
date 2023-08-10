package com.space.moviesapp.domain.usecase.details

import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    suspend fun invoke(movieId: Int): Flow<MovieDetailsModel> {
        return movieDetailsRepository.invoke(movieId)
    }
}