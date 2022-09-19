package com.example.flixster

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "MovieAdapter"
class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    //Expensive Operation


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }
    //Cheap operation
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
//        private val ivBackdrop = itemView.findViewById<ImageView>(R.id.ivBackdrop)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvtitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context)
                .load(movie.posterImageUrl)
                .placeholder(R.drawable.disk)
                .into(ivPoster)
//            val image: String
//            val orientation = ActivityInfo.SCREEN_ORIENTATION_USER//resources.configuration.orientation
//            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//                Glide.with(context)
//                    .load(movie.posterImageUrl)
//                    .placeholder(R.drawable.disk)
//                    .error(R.drawable.none)
//                    .into(ivPoster)
//            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                Glide.with(context)
//                    .load(movie.backDropImageUrl)
//                    .placeholder(R.drawable.disk)
//                    .error(R.drawable.none)
//                    .into(ivBackdrop)
//            }


        }
    }


}
