package com.space.moviesapp.domain.usecase

import com.space.moviesapp.domain.repository.MoviesRepository

class PopularMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
}