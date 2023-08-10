package com.space.moviesapp.domain.di


import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.details.GetMovieDetailsUseCase
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import com.space.moviesapp.domain.usecase.favourite.CheckFavouriteMovieUseCase
import com.space.moviesapp.domain.usecase.favourite.DeleteFavouriteMovieUseCase
import com.space.moviesapp.domain.usecase.favourite.GetFavouriteMovieUseCase
import com.space.moviesapp.domain.usecase.favourite.InsertFavouriteMovieUseCase
import com.space.moviesapp.domain.usecase.search.SearchMovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMovieCategoryUseCase(moviesRepository = get()) }
    single { SearchMovieUseCase(moviesRepository = get()) }
    single { GetMovieDetailsUseCase(movieDetailsRepository = get()) }
    single { GetMoviesUseCase(moviesRepository = get()) }
    single { GetFavouriteMovieUseCase(favouriteMovieRepository = get()) }
    single { InsertFavouriteMovieUseCase(favouriteMovieRepository = get()) }
    single { DeleteFavouriteMovieUseCase(favouriteMovieRepository = get()) }
    single { CheckFavouriteMovieUseCase(favouriteMovieRepository = get()) }
}