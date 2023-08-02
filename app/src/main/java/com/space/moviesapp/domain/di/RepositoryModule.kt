package com.space.moviesapp.domain.di

import com.space.moviesapp.data.repository.MoviesRepositoryImpl
import com.space.moviesapp.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(apiService = get()) }
}