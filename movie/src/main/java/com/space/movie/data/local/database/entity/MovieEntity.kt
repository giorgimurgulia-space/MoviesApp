package com.space.movie.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movie")
data class MovieEntity(
    @PrimaryKey()
    val id: Int,
    val genre: String,
    val title: String,
    val releaseDate: String,
    val backdropPosterPath: String,
    val mainPosterPath: String
)