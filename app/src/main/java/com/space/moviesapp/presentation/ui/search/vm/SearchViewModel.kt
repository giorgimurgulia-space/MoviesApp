package com.space.moviesapp.presentation.ui.search.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.resource.onError
import com.space.moviesapp.common.resource.onLoading
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.domain.usecase.favourite.ChangeMovieFavouriteStatusUseCase
import com.space.moviesapp.domain.usecase.favourite.GetFavouriteMovieUseCase
import com.space.moviesapp.domain.usecase.search.SearchMovieUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.moviesapp.presentation.common.mapper.MovieItemUIModelToEntity
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.model.MovieItemUIModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val changeMovieFavouriteStatusUseCase: ChangeMovieFavouriteStatusUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getFavouriteMovieUseCase: GetFavouriteMovieUseCase,
    private val movieItemUIModelToEntity: MovieItemUIModelToEntity,
    private val movieItemModelToUIMapper: MovieItemModelToUIMapper
) : BaseViewModel() {
    private val searchFlow =
        MutableSharedFlow<String?>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    private val _state = MutableStateFlow<PagingData<MovieItemUIModel>>(PagingData.empty())
    val state get() = _state.asStateFlow()

    private val _favouriteMovies = MutableStateFlow<List<Int>>(emptyList())
    val favouriteMovies get() = _favouriteMovies.asStateFlow()

    init {
        viewModelScope.launch {
            searchFlow
                .filterNotNull()
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    searchMovieUseCase
                        .invoke(query)
                        .cachedIn(viewModelScope)
                        .collectLatest {
                            _state.value = it.map { movieItem ->
                                movieItemModelToUIMapper(movieItem)
                            }
                        }
                }

        }

        getFavouriteMovie()
    }

    fun onFavouriteClick(movie: MovieItemUIModel) {
        viewModelScope.launch {
            changeMovieFavouriteStatusUseCase.invoke(movieItemUIModelToEntity(movie))
        }
    }

    fun movieSearch(query: String) {
        searchFlow.tryEmit(query)
    }

    private fun getFavouriteMovie() {
        viewModelScope.launch {
            getFavouriteMovieUseCase.invoke().toResult().collectLatest {
                it.onLoading {
                    setDialog(DialogItem.LoaderDialog())
                }
                it.onSuccess { movies ->
                    closeLoaderDialog()
                    _favouriteMovies.value = movies.map { item ->
                        item.id
                    }
                }
                it.onError {
                    setDialog(DialogItem.ErrorDialog(onRefreshClick = {
                        getFavouriteMovie()
                    }))
                }
            }
        }
    }
}