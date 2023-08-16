package com.space.moviesapp.presentation.di

import com.space.moviesapp.presentation.ui.home.mapper.MovieCategoryModelToUIMapper
import org.koin.dsl.module

val uiMapperModule = module {
    single { MovieCategoryModelToUIMapper() }
}