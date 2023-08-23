package com.space.movie.presentation.di

import com.space.movie.presentation.ui.details.mapper.MovieDetailsModelToUIMapper
import com.space.movie.presentation.ui.details.mapper.MovieDetailsUIModelToEntity
import com.space.movie.presentation.ui.home.mapper.MovieCategoryModelToUIMapper
import com.space.movie.presentation.common.mapper.MovieItemModelToUIMapper
import com.space.movie.presentation.common.mapper.MovieItemUIModelToEntity
import org.koin.dsl.module

val uiMapperModule = module {
    single { MovieCategoryModelToUIMapper() }
    single { MovieItemModelToUIMapper() }
    single { MovieItemUIModelToEntity() }
    single { MovieDetailsModelToUIMapper() }
    single { MovieDetailsUIModelToEntity() }
}