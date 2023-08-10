package com.space.moviesapp.data.remote.di

import com.space.moviesapp.presentation.ui.home.mapper.MovieCategoryModelToUIMapper
import org.koin.dsl.module

val remoteMapperModule = module {
    single { MovieCategoryModelToUIMapper() }
}