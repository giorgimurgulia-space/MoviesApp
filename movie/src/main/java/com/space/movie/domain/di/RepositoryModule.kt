package com.space.movie.domain.di


import com.space.movie.data.repository.FavouriteMovieRepositoryImpl
import com.space.movie.data.repository.MovieDetailsRepositoryImpl
import com.space.movie.data.repository.MoviesRepositoryImpl
import com.space.movie.domain.repository.FavouriteMovieRepository
import com.space.movie.domain.repository.MovieDetailsRepository
import com.space.movie.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            apiService = get(),
            movieCategoryDtoToDomainMapper = get(),
            movieGenresDtoToDomainMapper = get(),
            moviesPageDtoToDomainMapper = get()
        )
    }
    single<MovieDetailsRepository> {
        MovieDetailsRepositoryImpl(
            apiService = get(),
            moviesDao = get(),
            movieDetailDtoToDomainMapper = get(),
        )
    }
    single<FavouriteMovieRepository> {
        FavouriteMovieRepositoryImpl(
            moviesDao = get(),
            movieEntityToDomainModelMapper = get()
        )
    }
}