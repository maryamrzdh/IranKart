package com.mrz.irankart.ui.firstPage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrz.irankart.model.MovieTableModel
import com.mrz.irankart.model.Search
import com.mrz.irankart.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@HiltViewModel
class MovieViewModel  @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    var result= MutableLiveData<ArrayList<Search>>()

    var liveDataMovie: LiveData<MovieTableModel>? = null
    var liveDataMovieList: LiveData<ArrayList<MovieTableModel>>? = null

    fun insertData(context: Context,id:String, title: String, year: String,type:String,poster:String) {
        movieRepository.insertData(context, id,title, year,type,poster)
    }

    fun getLMoviesFromDb(context: Context):LiveData<List<MovieTableModel>>{
        return movieRepository.getLMoviesFromDb(context)
    }


    fun getFilms(){
        movieRepository.getMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .map{res->
                if (res.response.equals("True"))
                    return@map res.search
                else
                    return@map null
            }
                .subscribe(
                        {it ->result.postValue(it as ArrayList<Search>?) },
                        { _-> result.postValue(null)}
                )
    }


    fun getMovieResult():MutableLiveData<ArrayList<Search>>{
        return result
    }

    fun encodeToBase64(image: Bitmap, compressFormat: CompressFormat?, quality: Int): String {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT)
    }

    fun decodeBase64(input: String?): Bitmap? {
        val decodedBytes: ByteArray = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

}