package com.space.moviesapp.data.remote.api

import com.space.moviesapp.data.remote.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("dbdf9ee9-8083-425a-99f0-4850c8f87cc6")
    suspend fun getAllMovies(): Response<List<MoviesDto>>
}