package com.space.moviesapp.data.local.di

import com.space.moviesapp.data.local.mapper.MovieEntityToDomainModelMapper
import org.koin.dsl.module

val localMapperModule = module {
    single { MovieEntityToDomainModelMapper() }
}