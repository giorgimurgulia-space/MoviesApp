package com.space.movie.presentation.di

import com.space.movie.presentation.ui.details.vm.DetailsViewModel
import com.space.movie.presentation.ui.favourites.vm.FavouritesViewModel
import com.space.movie.presentation.ui.home.vm.HomeViewModel
import com.space.movie.presentation.ui.search.vm.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            getMoviesUseCase = get(),
            getMovieCategoryUseCase = get(),
            changeMovieFavouriteStatusUseCase = get(),
            movieCategoryModelToUIMapper = get(),
            movieItemModelToUIMapper = get(),
            movieItemUIModelToEntity = get(),
            getFavouriteMovieUseCase = get()
        )
    }
    viewModel {
        DetailsViewModel(
            getMovieDetailsUseCase = get(),
            changeMovieFavouriteStatusUseCase = get(),
            movieDetailsModelToUIMapper = get(),
            movieDetailsUIModelToEntity = get()
        )
    }
    viewModel {
        FavouritesViewModel(
            getFavouriteMovieUseCase = get(),
            changeMovieFavouriteStatusUseCase = get(),
            movieItemModelToUIMapper = get(),
            movieItemUIModelToEntity = get()
        )
    }
    viewModel {
        SearchViewModel(
            changeMovieFavouriteStatusUseCase = get(),
            searchMovieUseCase = get(),
            movieItemUIModelToEntity = get(),
            movieItemModelToUIMapper = get(),
            getFavouriteMovieUseCase = get()
        )
    }
}