package com.javohir.fizmasofttask.data.local.movies

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local.movies
 * Description: Dao
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getById(id: Int): MovieEntity?

    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun clear()

}