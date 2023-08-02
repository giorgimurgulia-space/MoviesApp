package com.space.moviesapp.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.maper.toUIModel
import com.space.moviesapp.common.resource.onError
import com.space.moviesapp.common.resource.onLoading
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieUIItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieCategoryUseCase: GetMovieCategoryUseCase
) : BaseViewModel() {

    private var currentPage = 0
    private var totalPages = 0
    private var selectCategoryIndex = 0

    private val _movieCategory = MutableStateFlow<List<MovieCategoryUIModel>>(emptyList())
    val movieCategory get() = _movieCategory.asStateFlow()

    private val _state = MutableStateFlow<List<MovieUIItem>>(emptyList())
    val state get() = _state.asStateFlow()

    fun getMovieCategory() {
        viewModelScope.launch {
            getMovieCategoryUseCase.invoke().toResult().collectLatest {
                it.onLoading {
                    setDialog(DialogItem.LoaderDialog())
                }
                it.onSuccess { category ->
                    closeLoaderDialog()
                    _movieCategory.tryEmit(category.map { item -> item.toUIModel() })
                }
                it.onError {
                    setDialog(DialogItem.ErrorDialog(onRefreshClick = { getMovieCategory() }))

                }
            }
        }
    }

    fun onFilterClick(index: Int) {
        selectCategoryIndex = index
        currentPage = 0
        _state.tryEmit(emptyList())

        getNewMovie()
    }

    fun onBottomScroll() {
        if (currentPage < totalPages)
            getNewMovie()
    }

    private fun getNewMovie() {
        viewModelScope.launch {
            getMoviesUseCase.invoke(
                _movieCategory.value[selectCategoryIndex].urlId, currentPage.inc()
            ).toResult()
                .collectLatest {
                    it.onLoading {
                        setDialog(DialogItem.LoaderDialog())
                    }
                    it.onSuccess { movies ->
                        closeLoaderDialog()

                        currentPage = movies.page
                        totalPages = movies.totalPages
                        _state.tryEmit(_state.value + (movies.toUIModel().results))
                    }
                    it.onError {
                        setDialog(DialogItem.ErrorDialog(onRefreshClick = { getNewMovie() }))
                    }
                }
        }
    }
}