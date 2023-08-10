package com.space.moviesapp.domain.usecase.favourite

import com.space.moviesapp.domain.repository.FavouriteMovieRepository

class CheckFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    suspend fun invoke(id: Int) = favouriteMovieRepository.isFavoriteMovie(id)
}