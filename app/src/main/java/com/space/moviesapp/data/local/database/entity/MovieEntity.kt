package com.space.moviesapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movie")
data class MovieEntity(
    @PrimaryKey()
    val id: Int,
    val genres: String,
    val title: String,
    val releaseDate: String,
    val poster: String
)