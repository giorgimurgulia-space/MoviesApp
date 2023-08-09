package com.space.moviesapp.domain.di


import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.GetMovieDetailsUseCase
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import com.space.moviesapp.domain.usecase.SearchMovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMovieCategoryUseCase(moviesRepository = get()) }
    single { SearchMovieUseCase(moviesRepository = get()) }
    single { GetMovieDetailsUseCase(movieDetailsRepository = get()) }
    single { GetMoviesUseCase(moviesRepository = get()) }
}