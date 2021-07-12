package com.mrz.irankart.ui.secondPage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrz.irankart.model.*
import com.mrz.irankart.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.Duration
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(var movieRepository: MovieRepository): ViewModel() {

    private var result=MutableLiveData<MovieDetails>()

    fun getMovieDetails(id:String){
        movieRepository.getMovieDetails(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {it ->result.postValue(it)},
                        { _-> result.postValue(null)}
                )
    }

    fun getMovieResult(): MutableLiveData<MovieDetails> {
        return result
    }

    fun insertDetails(context: Context,id: String, title: String, year: String, genre: String,poster:String,rating: String,duration: String) {
        movieRepository.insertDetails(context,id, title, year,genre,poster,rating,duration)

    }

    fun getMovieDetailsFromDb(context: Context,imdbId: String): LiveData<MovieDetailsTableModel> {
        return movieRepository.getLMovieDetails(context,imdbId)
    }
}