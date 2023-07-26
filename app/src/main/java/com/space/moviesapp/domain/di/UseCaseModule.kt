package com.space.moviesapp.domain.di


import com.space.moviesapp.domain.usecase.GetPopularMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetPopularMoviesUseCase(moviesRepository = get()) }
}