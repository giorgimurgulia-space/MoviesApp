package com.space.movie.data.remote.di

import com.space.movie.data.remote.mapper.GenresDtoToDomainMapper
import com.space.movie.data.remote.mapper.MovieCategoryDtoToDomainMapper
import com.space.movie.data.remote.mapper.MovieDetailDtoToDomainMapper
import com.space.movie.data.remote.mapper.MoviePageDtoToDomainMapper
import org.koin.dsl.module

val remoteMapperModule = module {
    single { GenresDtoToDomainMapper() }
    single { MovieCategoryDtoToDomainMapper() }
    single { MovieDetailDtoToDomainMapper() }
    single { MoviePageDtoToDomainMapper() }
}