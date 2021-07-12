package com.mrz.irankart.repository

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mrz.irankart.model.*
import com.mrz.irankart.repository.services.APIService
import com.mrz.irankart.room.MovieDatabase
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class MovieRepository(private var apiService: APIService) {

    companion object {

        private var movieDatabase: MovieDatabase? = null
        var movieTableModel: LiveData<MovieTableModel>? = null
    }
    private fun initializeDB(context: Context) : MovieDatabase {
        return MovieDatabase.getDataseClient(context)
    }

    fun insertData(context: Context,id: String, title: String, year: String,type:String,poster:String) {
        movieDatabase = initializeDB(context)
        CoroutineScope(IO).launch {
            val movieDetails = MovieTableModel(id,title, year,type,poster)
            movieDatabase!!.loginDao().insertData(movieDetails)
        }
    }

    fun insertDetails(context: Context, id: String,title: String, year: String,type:String,poster:String,rating: String) {
        movieDatabase = initializeDB(context)
        CoroutineScope(IO).launch {
            val movieDetails = MovieDetailsTableModel(id,title, year,type,poster,rating)
            movieDatabase!!.loginDao().insertDetails(movieDetails)
        }
    }

    fun getLMovieDetails(context: Context, imdbID: String) : LiveData<MovieDetailsTableModel> {
        movieDatabase = initializeDB(context)
        return movieDatabase!!.loginDao().getLMovieDetails(imdbID)
    }

    fun getLMoviesFromDb(context: Context) : LiveData<List<MovieTableModel>> {
        movieDatabase = initializeDB(context)
        return movieDatabase!!.loginDao().getLMoviesFromDb()
    }


    //server
    fun getMovies(): Observable<MovieResponse<Search>> {
        return apiService.getMovies()
    }

    fun getMovieDetails(id:String): Observable<MovieDetails> {
        return apiService.getMovieDetails(id)
    }
}