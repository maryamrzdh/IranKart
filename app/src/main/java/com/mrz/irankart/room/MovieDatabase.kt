package com.mrz.irankart.room

import android.content.Context
import androidx.room.*
import com.mrz.irankart.model.MovieDetailsTableModel
import com.mrz.irankart.model.MovieTableModel

@Database(entities = [MovieTableModel::class,MovieDetailsTableModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun loginDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDataseClient(context: Context) : MovieDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, MovieDatabase::class.java, "MOVIE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}