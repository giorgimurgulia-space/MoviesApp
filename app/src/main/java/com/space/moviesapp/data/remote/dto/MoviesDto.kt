package com.space.moviesapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MoviesDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    data class MovieDto(
        val id: Int,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double
    )
}