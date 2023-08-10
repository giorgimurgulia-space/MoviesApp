package com.space.moviesapp.domain.usecase.search

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class SearchMovieUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(query: String): Flow<PagingData<MovieItemModel>> {
        return moviesRepository.searchMovies(query)
    }
}