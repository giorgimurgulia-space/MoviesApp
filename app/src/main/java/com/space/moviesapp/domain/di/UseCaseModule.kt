package com.space.moviesapp.domain.di


import com.space.moviesapp.domain.usecase.GetMovieCategoryUseCase
import com.space.moviesapp.domain.usecase.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetMoviesUseCase(moviesRepository = get()) }
    single { GetMovieCategoryUseCase(moviesRepository = get()) }
}