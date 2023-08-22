package com.space.moviesapp.presentation.ui.favourites.vm

import androidx.lifecycle.viewModelScope
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.resource.onError
import com.space.moviesapp.common.resource.onLoading
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.domain.usecase.favourite.ChangeMovieFavouriteStatusUseCase
import com.space.moviesapp.domain.usecase.favourite.GetFavouriteMovieUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.model.MovieItemUIModel
import com.space.moviesapp.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.moviesapp.presentation.common.mapper.MovieItemUIModelToEntity
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
    private val _state = MutableStateFlow<List<MovieItemUIModel>>(emptyList())
    val state get() = _state.asStateFlow()

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
                    _state.value = movies.map { item ->
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