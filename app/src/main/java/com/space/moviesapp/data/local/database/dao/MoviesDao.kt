package com.space.moviesapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.space.moviesapp.data.local.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteMovie(movieEntity: MovieEntity)

    @Query("delete from favourite_movie where id = :id")
    suspend fun deleteFavouriteMovie(id: Int)

    @Query("select * from favourite_movie ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getFavouriteMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("select exists(select * from favourite_movie where id=:id)")
    suspend fun isFavouriteMovie(id: Int): Boolean
}
