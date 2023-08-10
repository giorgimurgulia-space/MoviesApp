package com.space.moviesapp.presentation.ui.details.vm

import androidx.lifecycle.viewModelScope
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.maper.toUIModel
import com.space.moviesapp.common.resource.onError
import com.space.moviesapp.common.resource.onLoading
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.domain.usecase.details.GetMovieDetailsUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import com.space.moviesapp.presentation.model.DialogItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : BaseViewModel() {
    private val _movieState = MutableStateFlow(MovieDetailsUIModel())
    val movieState get() = _movieState.asStateFlow()


    fun getMovie(movieId: Int?) {
        if (movieId == null) {
            setDialog(DialogItem.ErrorDialog(onRefreshClick = { }))
        } else {
            viewModelScope.launch {
                getMovieDetailsUseCase.invoke(movieId).toResult().collectLatest {
                    it.onLoading { setDialog(DialogItem.LoaderDialog()) }
                    it.onSuccess {
                        closeLoaderDialog()
                        _movieState.tryEmit(it.toUIModel())
                    }
                    it.onError {
                        setDialog(DialogItem.ErrorDialog(onRefreshClick = {
                            //todo
                        }))
                    }
                }
            }
        }
    }
}