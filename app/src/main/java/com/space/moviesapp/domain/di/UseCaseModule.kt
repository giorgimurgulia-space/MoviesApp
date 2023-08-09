package com.space.moviesapp.domain.di


import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.GetPopularMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMoviesUseCase(moviesRepository = get()) }
    single { GetMovieCategoryUseCase(moviesRepository = get()) }
    single { SearchMovieUseCase(moviesRepository = get()) }
    single { GetMovieDetailsUseCase(movieDetailsRepository = get()) }
}