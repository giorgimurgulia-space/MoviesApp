package com.space.movie.domain.usecase.search

import com.space.movie.domain.repository.MoviesRepository

class SearchMovieUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(query: String, page: Int) = moviesRepository.searchMovies(query, page)
}