package com.space.movie.domain.usecase.favourite

import com.space.movie.domain.model.MovieItemModel
import com.space.movie.domain.repository.FavouriteMovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteMovieUseCase(private val favouriteMovieRepository: FavouriteMovieRepository) {
    fun invoke(): Flow<List<MovieItemModel>> = favouriteMovieRepository.getFavoriteMovie()
}