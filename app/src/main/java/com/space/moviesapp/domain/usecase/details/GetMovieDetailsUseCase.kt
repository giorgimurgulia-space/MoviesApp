package com.space.moviesapp.domain.usecase.details

import com.space.moviesapp.domain.model.MovieDetailsModel
import com.space.moviesapp.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    fun invoke(movieId: Int) = movieDetailsRepository.invoke(movieId)
}