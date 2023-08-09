package com.space.moviesapp.domain.usecase

import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    suspend fun invoke(movieId: Int): Flow<MovieDetailsModel> {
        return movieDetailsRepository.getMovie(movieId)
    }
}