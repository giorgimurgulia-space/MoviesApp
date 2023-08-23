package com.space.moviesapp.data.remote.di

import com.space.core.utils.MoviesConstants.BASE_URL
import com.space.core.utils.MoviesConstants.API_KEY
import com.space.moviesapp.data.remote.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun createInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Authorization", API_KEY)
            .build()
        chain.proceed(request)
    }
}

private fun creteOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createInterceptor())
        .build()
}

private fun createRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(creteOkhttpClient())
        .build()
}

val networkModule = module {
    single { createRetrofit() }
    single { get<Retrofit>().create(ApiService::class.java) }
}