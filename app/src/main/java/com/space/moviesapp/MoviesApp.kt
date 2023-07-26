package com.space.moviesapp

import android.app.Application
import com.space.moviesapp.data.remote.di.networkModule
import com.space.moviesapp.domain.di.repositoryModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(
                networkModule,
                repositoryModule,
            )
        }
    }
}