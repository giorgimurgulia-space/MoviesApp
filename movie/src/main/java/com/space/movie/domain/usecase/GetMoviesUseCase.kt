package com.space.movie.domain.usecase

import com.space.movie.domain.repository.MoviesRepository

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(categoryId: String, page: Int) = moviesRepository.getMovies(categoryId, page)
}