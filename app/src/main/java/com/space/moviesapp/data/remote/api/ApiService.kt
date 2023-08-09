package com.space.moviesapp.data.remote.api

import com.space.moviesapp.data.remote.dto.GenresDto
import com.space.moviesapp.data.remote.dto.MovieDetailsDto
import com.space.moviesapp.data.remote.dto.MoviesPageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{categoryId}")
    suspend fun getMoviesPage(
        @Path(value = "categoryId") categoryId: String,
        @Query("page") page: Int
    ): Response<MoviesPageDto>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenresDto>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MoviesPageDto>

    @GET("movie/{movieId}")
    suspend fun getMovie(@Path(value = "movieId") movieId: Int): Response<MovieDetailsDto>
}