package com.space.moviesapp.presentation.di

import com.space.moviesapp.presentation.ui.home.mapper.MovieCategoryModelToUIMapper
import com.space.moviesapp.presentation.ui.home.mapper.MovieItemModelToUIMapper
import com.space.moviesapp.presentation.ui.home.mapper.MovieItemUIModelToEntity
import org.koin.dsl.module

val uiMapperModule = module {
    single { MovieCategoryModelToUIMapper() }
    single { MovieItemModelToUIMapper() }
    single { MovieItemUIModelToEntity() }
}