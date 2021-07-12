package com.mrz.irankart.ui.firstPage

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrz.irankart.R
import com.mrz.irankart.model.Search
import com.squareup.picasso.Picasso


class RecyclerAdapter(private val list: ArrayList<Search>,private val cellClickListener: ClickListener) : RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>()  {


    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_items, parent, false)){
        private var mTitleView: TextView? = null
        private var mYearView: TextView? = null
        private var mTypeView: TextView? = null
        private var mImageView: ImageView? = null
        private var mView: LinearLayout? = null


        init {
            mTitleView = itemView.findViewById(R.id.list_title)
            mYearView = itemView.findViewById(R.id.list_year)
            mTypeView = itemView.findViewById(R.id.list_type)
            mImageView = itemView.findViewById(R.id.movie_poster)
            mView = itemView.findViewById(R.id.item_row)
//            itemView.setOnClickListener { ClickListener.onItemClick() }
        }

        fun bind(movie: Search) {
            mTitleView?.text = movie.title
            mYearView?.text = movie.year.toString()
            mTypeView?.text = movie.type

            if (movie.img!=null) {
                mImageView!!.setImageBitmap(movie.img)
            }
            else
            Picasso.get().load(movie.poster).into(mImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Search = list[position]
        holder.itemView.setOnClickListener {
            cellClickListener.onItemClick(movie.imdbID!!)
        }
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

    interface ClickListener {
        fun onItemClick(id: String)
    }
}

