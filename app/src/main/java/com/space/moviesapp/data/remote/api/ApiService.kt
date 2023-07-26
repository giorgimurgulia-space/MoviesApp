package com.space.moviesapp.data.remote.api

import com.space.moviesapp.data.remote.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesDto>

    @GET("search/top_rated")
    suspend fun getTopMovies(): Response<MoviesDto>

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String): Response<MoviesDto>
}