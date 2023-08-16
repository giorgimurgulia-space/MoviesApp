package com.space.moviesapp.data.remote.di

import com.space.moviesapp.data.remote.mapper.GenresDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MovieDetailDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MovieItemDtoToDomainMapper
import com.space.moviesapp.presentation.ui.home.mapper.MovieCategoryModelToUIMapper
import org.koin.dsl.module

val remoteMapperModule = module {
    single { GenresDtoToDomainMapper() }
    single { MovieCategoryModelToUIMapper() }
    single { MovieDetailDtoToDomainMapper() }
    single { MovieItemDtoToDomainMapper() }
}