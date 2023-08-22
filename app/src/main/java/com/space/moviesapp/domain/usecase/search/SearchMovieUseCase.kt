package com.space.moviesapp.domain.usecase.search

import androidx.paging.PagingData
import com.space.moviesapp.domain.model.MovieItemModel
import com.space.moviesapp.domain.model.MoviesPageModel
import com.space.moviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class SearchMovieUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(query: String, page: Int): MoviesPageModel {
        return moviesRepository.searchMovies(query, page)
    }
}