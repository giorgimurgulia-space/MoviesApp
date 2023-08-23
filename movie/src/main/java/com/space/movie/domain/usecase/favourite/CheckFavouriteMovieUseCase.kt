package com.space.movie.domain.usecase.favourite

import com.space.movie.domain.repository.FavouriteMovieRepository

class CheckFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(id: Int) = favouriteMovieRepository.isFavoriteMovie(id)
}