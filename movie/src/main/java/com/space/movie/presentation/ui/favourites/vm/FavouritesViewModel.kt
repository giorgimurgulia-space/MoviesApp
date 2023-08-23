package com.space.movie.presentation.ui.favourites.vm

import androidx.lifecycle.viewModelScope
import com.space.core.extensions.toResult
import com.space.core.resource.onError
import com.space.core.resource.onLoading
import com.space.core.resource.onSuccess
import com.space.movie.domain.usecase.favourite.ChangeMovieFavouriteStatusUseCase
import com.space.movie.domain.usecase.favourite.GetFavouriteMovieUseCase
import com.space.core.base.vm.BaseViewModel
import com.space.core.view.dialog.DialogItem
import com.space.movie.presentation.model.MovieItemUIModel
import com.space.movie.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.movie.presentation.common.mapper.MovieItemUIModelToEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val getFavouriteMovieUseCase: GetFavouriteMovieUseCase,
    private val changeMovieFavouriteStatusUseCase: ChangeMovieFavouriteStatusUseCase,
    private val movieItemModelToUIMapper: MovieItemModelToUIMapper,
    private val movieItemUIModelToEntity: MovieItemUIModelToEntity
) : BaseViewModel() {

    private val _favouriteUIState = MutableStateFlow<List<MovieItemUIModel>>(emptyList())
    val favouriteUIState get() = _favouriteUIState.asStateFlow()

    init {
        getFavouriteMovie()
    }

    fun onFavouriteClick(movie: MovieItemUIModel) {
        viewModelScope.launch {
            changeMovieFavouriteStatusUseCase.invoke(movieItemUIModelToEntity(movie))
        }
    }

    private fun getFavouriteMovie() {
        viewModelScope.launch {
            getFavouriteMovieUseCase.invoke().toResult().collectLatest {
                it.onLoading {
                    setDialog(DialogItem.LoaderDialog())
                }
                it.onSuccess { movies ->
                    closeLoaderDialog()
                    _favouriteUIState.value = movies.map { item ->
                        movieItemModelToUIMapper.invoke(item, true)
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