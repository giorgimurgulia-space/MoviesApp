package com.space.moviesapp.domain.usecase

import com.space.moviesapp.domain.repository.MoviesRepository

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(categoryId: String, page: Int) = moviesRepository.getMovies(categoryId, page)
}