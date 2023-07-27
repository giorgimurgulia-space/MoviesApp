package com.space.moviesapp.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.maper.toUIModel
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieItemUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieCategoryUseCase: GetMovieCategoryUseCase,
) : BaseViewModel() {

    private var currentPage = 0
    private var totalPages = 0
    private var selectCategoryIndex = 0

    private val _movieCategory = MutableStateFlow<List<MovieCategoryUIModel>>(emptyList())
    val movieCategory get() = _movieCategory.asStateFlow()

    private val _state = MutableStateFlow<List<MovieItemUIModel>>(emptyList())
    val state get() = _state.asStateFlow()

    fun getMovieCategory() {
        viewModelScope.launch {
            _movieCategory.tryEmit(getMovieCategoryUseCase.invoke().map {
                it.toUIModel()
            })
        }
    }

    fun onFilterClick(selectCategory: List<Int>) {
        selectCategoryIndex = selectCategory.first()
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
                _movieCategory.value[selectCategoryIndex].id,
                currentPage.inc()
            ).toResult()
                .collectLatest {
                    it.onSuccess { movies ->
                        currentPage = movies.page
                        totalPages = movies.totalPages
                        _state.tryEmit(_state.value + (movies.toUIModel().results))
                    }
                }
        }
    }
}