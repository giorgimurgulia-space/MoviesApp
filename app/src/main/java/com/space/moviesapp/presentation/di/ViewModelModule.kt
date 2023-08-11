package com.space.moviesapp.presentation.di

import com.space.moviesapp.presentation.ui.home.vm.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getMoviesUseCase = get(), getMovieCategoryUseCase = get()) }
}