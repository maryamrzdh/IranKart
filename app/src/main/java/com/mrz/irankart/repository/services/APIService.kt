package com.mrz.irankart.repository.services

import com.mrz.irankart.model.MovieDetails
import com.mrz.irankart.model.MovieResponse
import com.mrz.irankart.model.Search
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface APIService {

    /**
     *
     */
    @GET("?apikey=3e974fca&s=batman/")
    fun getMovies(): Observable<MovieResponse<Search>>


    /**
     *
     */

    @GET("?apikey=3e974fca")
    fun getMovieDetails(@Query("i") imdbID: String): Observable<MovieDetails>

//    Example: http://www.omdbapi.com/?apikey=3e974fca&i=tt0372784

}