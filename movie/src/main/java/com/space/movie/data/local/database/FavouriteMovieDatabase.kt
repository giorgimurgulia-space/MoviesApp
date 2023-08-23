package com.space.movie.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.movie.data.local.database.dao.MoviesDao
import com.space.movie.data.local.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class FavouriteMovieDatabase : RoomDatabase() {
    abstract fun favouriteMovieDao(): MoviesDao
}