package com.space.movie.domain.usecase

import com.space.movie.domain.repository.MoviesRepository

class GetMovieCategoryUseCase(private val moviesRepository: MoviesRepository) {
    fun invoke() = moviesRepository.getMovieCategory()
}