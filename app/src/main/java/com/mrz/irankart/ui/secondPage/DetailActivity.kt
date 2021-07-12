package com.mrz.irankart.ui.secondPage

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mrz.irankart.BaseActivity
import com.mrz.irankart.R
import com.mrz.irankart.databinding.ActivityDetailBinding
import com.mrz.irankart.databinding.ActivityMainBinding
import com.mrz.irankart.model.MovieDetails
import com.mrz.irankart.model.Search
import com.mrz.irankart.ui.firstPage.MainActivity
import com.mrz.irankart.ui.firstPage.MovieViewModel
import com.mrz.irankart.ui.firstPage.RecyclerAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var imdbId=intent.extras!!.get("imdbId").toString()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        if (imdbId!="") {
            if (isNetworkConnected())
                viewModel.getMovieDetails(imdbId)
            else
                viewModel.getMovieDetailsFromDb(this,imdbId).observe(this,{it->
                    hideLoading()

                    if (it!=null){
                        var movieDetail=MovieDetails()
                        movieDetail.title=it.Title
                        movieDetail.runtime=it.duration
                        binding.movie=movieDetail
                    }

                })
        }

        showLoading()
        observe()
    }

    override fun observe() {
        viewModel.getMovieResult().observe(this,{ it->
            hideLoading()
            if (it!=null){
                binding.movie=it
                Picasso.get().load(it.poster).into(binding.imageView)
                var rate= it.ratings!![0]
                binding.rating.text=rate.value
                viewModel.insertDetails(this,it.imdbID!!, it.title!!, it.year!!,it.genre!!,it.poster!!,rate.value!!,it.runtime!!)
            }
            else
                Toast.makeText(this,"error Loading from server!", Toast.LENGTH_SHORT).show()
        })
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var binding: ActivityDetailBinding
        lateinit var viewModel:DetailsViewModel
    }
}