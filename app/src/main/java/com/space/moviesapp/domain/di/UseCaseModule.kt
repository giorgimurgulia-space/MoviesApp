package com.space.moviesapp.domain.di


import com.space.moviesapp.domain.usecase.PopularMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { PopularMoviesUseCase(moviesRepository = get()) }
}