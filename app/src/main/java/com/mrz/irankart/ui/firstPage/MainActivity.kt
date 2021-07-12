package com.mrz.irankart.ui.firstPage

import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mrz.irankart.BaseActivity
import com.mrz.irankart.R
import com.mrz.irankart.databinding.ActivityMainBinding
import com.mrz.irankart.model.MovieTableModel
import com.mrz.irankart.model.Search
import com.mrz.irankart.ui.secondPage.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.Url
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


@AndroidEntryPoint
class MainActivity : BaseActivity() , RecyclerAdapter.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel=ViewModelProvider(this).get(MovieViewModel::class.java)
        loadView()
        observe()

    }

    private fun loadView() {
        showLoading()

        if (isNetworkConnected())
            viewModel.getFilms()
        else
            viewModel.getLMoviesFromDb(this).observe(this,{it->
                var list= ArrayList<Search>()
                hideLoading()

                if (it!=null && it.isNotEmpty()) {
                    for (i in it) {
                        val myBitmapAgain: Bitmap? = viewModel.decodeBase64(i.poster)

                        var search = Search()
                        search.imdbID=i.id
                        search.title = i.Title
                        search.year = i.Year
                        search.type = i.Type
//                       search.poster = i.poster
                        search.img=myBitmapAgain
                        list.add(search)
                    }


                    binding.listRecyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        // set the custom adapter to the RecyclerView
                        adapter = RecyclerAdapter(list, this@MainActivity)
                    }
                }
                else
                    Toast.makeText(this,"database is empty",Toast.LENGTH_SHORT).show()
            })

    }

    override fun observe() {
        viewModel.getMovieResult().observe(this,{it->
            hideLoading()
            if (it!=null){
                binding.listRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    // set the custom adapter to the RecyclerView
                    adapter = RecyclerAdapter(it,this@MainActivity)
                }
                for(i in it) {
                    DownloadImagesTask().execute(i)
                }
            }
            else
                Toast.makeText(this,"error Loading from server!",Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        loadView()

    }
    companion object{
        var movieList= ArrayList<Search>()
        lateinit var adapter:RecyclerAdapter
        lateinit var binding: ActivityMainBinding
        lateinit var viewModel:MovieViewModel
        var myBase64Image: String=""

    }

    override fun onItemClick(id: String) {
        val intent= Intent(this,DetailActivity::class.java)
        intent.putExtra("imdbId",id)
        startActivity(intent)
    }


     class DownloadImagesTask : AsyncTask<Search, Void, Bitmap>() {
         override fun onPostExecute(result: Bitmap?) {
             super.onPostExecute(result)
                myBase64Image = viewModel.encodeToBase64(result!!, Bitmap.CompressFormat.JPEG, 100)
         }

          fun downloadImage(search: Search): Bitmap? {
             return try {
                 val connection =
                     URL(search.poster!!).openConnection() as HttpURLConnection
                 connection.doInput = true
                 connection.connect()
                 val input = connection.inputStream
                 BitmapFactory.decodeStream(input)
             } catch (e: IOException) {
                 // Log exception
                 null
             }
         }

         override fun doInBackground(vararg params: Search): Bitmap? {
             return downloadImage(params[0])
         }
     }

    }