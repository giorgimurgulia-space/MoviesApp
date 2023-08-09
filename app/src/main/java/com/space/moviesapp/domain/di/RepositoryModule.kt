package com.space.moviesapp.domain.di

import com.space.moviesapp.data.repository.MovieDetailsRepositoryImpl
import com.space.moviesapp.data.repository.MoviesRepositoryImpl
import com.space.moviesapp.domain.repository.MovieDetailsRepository
import com.space.moviesapp.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(apiService = get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(apiService = get()) }
}