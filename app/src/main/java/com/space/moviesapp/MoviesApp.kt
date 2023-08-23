package com.space.moviesapp

import android.app.Application
import com.space.movie.data.local.di.dataBaseModule
import com.space.movie.data.local.di.localMapperModule
import com.space.movie.data.remote.di.networkModule
import com.space.movie.data.remote.di.remoteMapperModule
import com.space.movie.domain.di.repositoryModule
import com.space.movie.domain.di.useCaseModule
import com.space.movie.presentation.di.uiMapperModule
import com.space.movie.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                dataBaseModule,
                remoteMapperModule,
                localMapperModule,
                uiMapperModule
            )
        }
    }
}