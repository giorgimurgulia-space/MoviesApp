package com.space.moviesapp.presentation.ui.search.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.space.moviesapp.domain.usecase.favourite.ChangeMovieFavouriteStatusUseCase
import com.space.moviesapp.domain.usecase.favourite.GetFavouriteMovieUseCase
import com.space.moviesapp.domain.usecase.search.SearchMovieUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.moviesapp.presentation.common.mapper.MovieItemUIModelToEntity
import com.space.moviesapp.presentation.model.MovieItemUIModel
import com.space.moviesapp.presentation.ui.search.paging.MoviesSearchPagingSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val changeMovieFavouriteStatusUseCase: ChangeMovieFavouriteStatusUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getFavouriteMovieUseCase: GetFavouriteMovieUseCase,
    private val movieItemUIModelToEntity: MovieItemUIModelToEntity,
    private val movieItemModelToUIMapper: MovieItemModelToUIMapper
) : BaseViewModel() {

    private val searchFlow = MutableSharedFlow<String?>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    // todo name
    private val _state = MutableStateFlow<PagingData<MovieItemUIModel>>(PagingData.empty())
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            searchFlow
                .filterNotNull()
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    combine(
                        Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                            pagingSourceFactory = {
                                MoviesSearchPagingSource(
                                    searchMovieUseCase, query
                                )
                            }).flow.cachedIn(viewModelScope),
                        getFavouriteMovieUseCase.invoke()
                    ) { movies, favourites ->
                        movies to favourites
                    }.collectLatest { (movies, favourites) ->
                        _state.value = movies.map { movie ->
                            movieItemModelToUIMapper.invoke(movie,
                                favourites.any { it.id == movie.id })
                        }

                    }
                }
        }
    }

    fun onFavouriteClick(movie: MovieItemUIModel) {
        viewModelScope.launch {
            changeMovieFavouriteStatusUseCase.invoke(movieItemUIModelToEntity(movie))
        }
    }

    fun movieSearch(query: String) {
        searchFlow.tryEmit(query)
    }
}