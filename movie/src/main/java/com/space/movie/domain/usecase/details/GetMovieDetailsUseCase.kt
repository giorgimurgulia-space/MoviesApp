package com.space.movie.domain.usecase.details

import com.space.movie.domain.repository.MovieDetailsRepository

class GetMovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) {
    fun invoke(movieId: Int) = movieDetailsRepository.invoke(movieId)
}