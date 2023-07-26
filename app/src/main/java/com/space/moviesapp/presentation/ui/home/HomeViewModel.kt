package com.space.moviesapp.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.domain.usecase.GetPopularMoviesUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.model.MovieUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<List<MovieUIModel>>(emptyList())
    val state get() = _state.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.invoke(3).toResult().collectLatest {
                it.onSuccess { movies ->
                    _state.tryEmit(
                        movies.map { movie ->
                            MovieUIModel(
                                movie.id,
                                movie.title,
                                movie.rating,
                                movie.releaseDate,
                                movie.poster
                            )
                        })
                }
            }
        }
    }

    fun onFilterClick(id: Int) {

    }
}