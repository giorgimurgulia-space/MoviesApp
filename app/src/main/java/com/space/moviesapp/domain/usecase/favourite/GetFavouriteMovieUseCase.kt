package com.space.moviesapp.domain.usecase.favourite

import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    fun invoke(): Flow<List<MovieItemModel>> = favouriteMovieRepository.getFavoriteMovie()
}