package com.space.movie.presentation.ui.search.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.space.movie.domain.usecase.favourite.ChangeMovieFavouriteStatusUseCase
import com.space.movie.domain.usecase.favourite.GetFavouriteMovieUseCase
import com.space.movie.domain.usecase.search.SearchMovieUseCase
import com.space.core.base.vm.BaseViewModel
import com.space.movie.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.movie.presentation.common.mapper.MovieItemUIModelToEntity
import com.space.movie.presentation.model.MovieItemUIModel
import com.space.movie.presentation.ui.search.paging.MoviesSearchPagingSource
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

    private val _searchUIState = MutableStateFlow<PagingData<MovieItemUIModel>>(PagingData.empty())
    val searchUIState get() = _searchUIState.asStateFlow()

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
                        _searchUIState.value = movies.map { movie ->
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