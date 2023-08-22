package com.space.moviesapp.presentation.ui.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.space.moviesapp.common.extensions.toResult
import com.space.moviesapp.common.resource.onError
import com.space.moviesapp.common.resource.onLoading
import com.space.moviesapp.common.resource.onSuccess
import com.space.moviesapp.presentation.ui.home.paging.MoviesPagingSource
import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import com.space.moviesapp.domain.usecase.favourite.ChangeMovieFavouriteStatusUseCase
import com.space.moviesapp.domain.usecase.favourite.GetFavouriteMovieUseCase
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.moviesapp.presentation.common.mapper.MovieItemUIModelToEntity
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.model.MovieCategoryUIModel
import com.space.moviesapp.presentation.model.MovieItemUIModel
import com.space.moviesapp.presentation.navigation.MovieEvent
import com.space.moviesapp.presentation.ui.home.mapper.MovieCategoryModelToUIMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieCategoryUseCase: GetMovieCategoryUseCase,
    private val changeMovieFavouriteStatusUseCase: ChangeMovieFavouriteStatusUseCase,
    private val getFavouriteMovieUseCase: GetFavouriteMovieUseCase,
    private val movieCategoryModelToUIMapper: MovieCategoryModelToUIMapper,
    private val movieItemModelToUIMapper: MovieItemModelToUIMapper,
    private val movieItemUIModelToEntity: MovieItemUIModelToEntity,
) : BaseViewModel() {

    private var selectCategoryIndex = 0
    private var movieCategoryList = emptyList<MovieCategoryUIModel>()

    private val _movieCategory = MutableLiveData<MovieEvent<List<MovieCategoryUIModel>>>()
    val movieCategory get() = _movieCategory

    private var _state = MutableStateFlow<PagingData<MovieItemUIModel>>(PagingData.empty())
    val state get() = _state.asStateFlow()

    init {
        getMovieCategory()
    }

    fun onFavouriteClick(movie: MovieItemUIModel) {
        viewModelScope.launch {
            changeMovieFavouriteStatusUseCase.invoke(movieItemUIModelToEntity(movie))
        }
    }

    fun onFilterClick(index: Int) {
        if (index >= 0) {
            selectCategoryIndex = index
            _state.tryEmit(PagingData.empty())
            getNewMovie()
        }
    }

    private fun getMovieCategory() {
        viewModelScope.launch {
            getMovieCategoryUseCase.invoke().toResult().collectLatest {
                it.onLoading {
                    setDialog(DialogItem.LoaderDialog())
                }
                it.onSuccess { category ->
                    closeLoaderDialog()
                    movieCategoryList = category.map { item -> movieCategoryModelToUIMapper(item) }
                    _movieCategory.value = MovieEvent(movieCategoryList)
                }
                it.onError {
                    setDialog(DialogItem.ErrorDialog(onRefreshClick = { getMovieCategory() }))
                }
            }
        }
    }

    private fun getNewMovie() {
        viewModelScope.launch {
            combine(
                Pager(
                    config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                    pagingSourceFactory = {
                        MoviesPagingSource(
                            getMoviesUseCase,
                            movieCategoryList[selectCategoryIndex].urlId
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                getFavouriteMovieUseCase.invoke(),
            ) { movies, favourites ->
                movies to favourites
            }.collectLatest { (movies, favourites) ->
                _state.value = movies.map { movie ->
                    movieItemModelToUIMapper.invoke(movie, favourites.any { it.id == movie.id })
                }
            }
        }
    }
}
