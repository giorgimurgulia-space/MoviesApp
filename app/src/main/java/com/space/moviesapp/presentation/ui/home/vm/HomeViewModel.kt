package com.space.moviesapp.presentation.ui.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.maper.toUIModel
import com.space.moviesapp.common.resource.onError
import com.space.moviesapp.common.resource.onLoading
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import com.space.moviesapp.domain.usecase.SearchMovieUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieDetailsUIModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieCategoryUseCase: GetMovieCategoryUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : BaseViewModel() {

    private var selectCategoryIndex = 0

    private val _movieCategory = MutableStateFlow<List<MovieCategoryUIModel>>(emptyList())
    val movieCategory get() = _movieCategory.asStateFlow()

    private val _state = MutableStateFlow<PagingData<MovieDetailsUIModel>>(PagingData.empty())
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
        _state.tryEmit(PagingData.empty())

        if (index >= 0) {
            getNewMovie()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            searchMovieUseCase.invoke(query).collectLatest { movieItem ->
                _state.value = movieItem.map { it.toUIModel() }
            }
        }
    }


    private fun getNewMovie() {
        viewModelScope.launch {
            getMoviesUseCase.invoke(
                _movieCategory.value[selectCategoryIndex].urlId
            ).collectLatest { movieItem ->
                _state.value = movieItem.map { it.toUIModel() }
            }
        }
    }
}