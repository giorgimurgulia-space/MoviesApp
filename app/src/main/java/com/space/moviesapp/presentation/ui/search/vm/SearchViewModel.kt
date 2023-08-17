package com.space.moviesapp.presentation.ui.search.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.moviesapp.domain.usecase.favourite.ChangeMovieFavouriteStatusUseCase
import com.space.moviesapp.domain.usecase.search.SearchMovieUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.moviesapp.presentation.common.mapper.MovieItemUIModelToEntity
import com.space.moviesapp.presentation.model.MovieItemUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewModel(
    private val changeMovieFavouriteStatusUseCase: ChangeMovieFavouriteStatusUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val movieItemUIModelToEntity: MovieItemUIModelToEntity,
    private val movieItemModelToUIMapper: MovieItemModelToUIMapper,

    ) : BaseViewModel() {
    private val _state = MutableStateFlow<PagingData<MovieItemUIModel>>(PagingData.empty())
    val state get() = _state.asStateFlow()

    fun onFavouriteClick(movie: MovieItemUIModel) {
        viewModelScope.launch {
            changeMovieFavouriteStatusUseCase.invoke(movieItemUIModelToEntity(movie))
        }
    }


    fun movieSearch(query: String) {
        viewModelScope.launch {
            searchMovieUseCase.invoke(query).cachedIn(viewModelScope).collectLatest { movieItem ->
                _state.value = movieItem.map { movieItemModelToUIMapper(it) }
            }
        }
    }
}