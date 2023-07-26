package com.space.moviesapp.domain.usecase

import com.space.moviesapp.domain.repository.MoviesRepository

class GetMovieCategoryUseCase(
    private val moviesRepository: MoviesRepository
) {
    fun invoke() = moviesRepository.getMovieCategory()
}