package com.space.moviesapp.presentation.di

import com.space.moviesapp.presentation.ui.details.mapper.MovieDetailsModelToUIMapper
import com.space.moviesapp.presentation.ui.details.mapper.MovieDetailsUIModelToEntity
import com.space.moviesapp.presentation.ui.home.mapper.MovieCategoryModelToUIMapper
import com.space.moviesapp.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.moviesapp.presentation.common.mapper.MovieItemUIModelToEntity
import org.koin.dsl.module

val uiMapperModule = module {
    single { MovieCategoryModelToUIMapper() }
    single { MovieItemModelToUIMapper() }
    single { MovieItemUIModelToEntity() }
    single { MovieDetailsModelToUIMapper() }
    single { MovieDetailsUIModelToEntity() }
}