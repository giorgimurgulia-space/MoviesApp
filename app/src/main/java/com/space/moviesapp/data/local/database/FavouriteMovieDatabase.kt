package com.space.moviesapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.moviesapp.data.local.database.dao.MoviesDao
import com.space.moviesapp.data.local.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class FavouriteMovieDatabase : RoomDatabase() {
    abstract fun favouriteMovieDao(): MoviesDao
}