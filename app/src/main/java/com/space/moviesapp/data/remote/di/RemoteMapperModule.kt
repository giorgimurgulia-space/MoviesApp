package com.space.moviesapp.data.remote.di

import com.space.moviesapp.data.remote.mapper.*
import org.koin.dsl.module

val remoteMapperModule = module {
    single { GenresDtoToDomainMapper() }
    single { MovieCategoryDtoToDomainMapper() }
    single { MovieDetailDtoToDomainMapper() }
    single { MovieItemDtoToDomainMapper() }
    single { MoviePageDtoToDomainMapper() }
}