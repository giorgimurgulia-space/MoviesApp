package com.space.movie.data.local.di

import com.space.movie.data.local.mapper.MovieEntityToDomainModelMapper
import org.koin.dsl.module

val localMapperModule = module {
    single { MovieEntityToDomainModelMapper() }
}