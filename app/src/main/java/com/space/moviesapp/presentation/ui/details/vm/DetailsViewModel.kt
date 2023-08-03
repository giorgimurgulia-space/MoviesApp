package com.space.moviesapp.presentation.ui.details.vm

import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.model.DetailMovieUI
import com.space.moviesapp.presentation.model.DialogItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel : BaseViewModel() {
    private val _movieState = MutableStateFlow(DetailMovieUI())
    val movieState get() = _movieState.asStateFlow()


    fun getMovie(movieId: String?) {
        if (movieId.isNullOrEmpty()) {
            setDialog(DialogItem.ErrorDialog(onRefreshClick = { }))
        }
    }
}