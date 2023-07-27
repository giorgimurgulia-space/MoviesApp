package com.space.moviesapp.data.remote.api

import com.space.moviesapp.data.remote.dto.GenresDto
import com.space.moviesapp.data.remote.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{categoryId}")
    suspend fun getPopularMovies(
        @Path(value = "categoryId") categoryId: String,
        @Query("page") page: Int
    ): Response<MoviesDto>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenresDto>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MoviesDto>
}