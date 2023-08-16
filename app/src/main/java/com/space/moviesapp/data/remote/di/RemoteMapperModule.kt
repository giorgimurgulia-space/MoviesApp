package com.space.moviesapp.data.remote.di

import com.space.moviesapp.data.remote.mapper.GenresDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MovieCategoryDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MovieDetailDtoToDomainMapper
import com.space.moviesapp.data.remote.mapper.MovieItemDtoToDomainMapper
import org.koin.dsl.module

val remoteMapperModule = module {
    single { GenresDtoToDomainMapper() }
    single { MovieCategoryDtoToDomainMapper() }
    single { MovieDetailDtoToDomainMapper() }
    single { MovieItemDtoToDomainMapper() }
}