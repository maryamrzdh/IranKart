package com.mrz.irankart.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrz.irankart.model.MovieDetailsTableModel
import com.mrz.irankart.model.MovieTableModel

@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(movieTableModel: MovieTableModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(movieDetailTableModel: MovieDetailsTableModel)

    @Query("SELECT * FROM Movies")
    fun getLMoviesFromDb() : LiveData<List<MovieTableModel>>

    @Query("SELECT * FROM MoviesDetails WHERE  imdbID=:imdbID")
    fun getLMovieDetails(imdbID: String) : LiveData<MovieDetailsTableModel>
}