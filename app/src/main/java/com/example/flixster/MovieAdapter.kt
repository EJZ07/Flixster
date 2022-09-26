package com.example.flixster

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL


const val MOVIE_EXTRA = "MOVIE_EXTRA"
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val ivBackdrop = itemView.findViewById<ImageView>(R.id.ivBackdrop)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvtitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
//            Glide.with(context)
//                .load(movie.posterImageUrl)
//                .placeholder(R.drawable.disk)
//                .into(ivPoster)
            val image: String
            val orientation = context.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide.with(context)
                    .load(movie.posterImageUrl)
                    .override(100, Target.SIZE_ORIGINAL)
                    .transform(RoundedCorners(30))
                    .placeholder(R.drawable.disk)
                    .into(ivPoster)
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Glide.with(context)
                    .load(movie.posterImageUrl)
                    .override(500, Target.SIZE_ORIGINAL)
                    .transform(RoundedCorners(30))
                    .placeholder(R.drawable.disk)
                    .into(ivBackdrop)
            }


        }

        override fun onClick(v: View?) {
            //Get notified of movie being tapped on
            val movie = movies[adapterPosition]
//            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show ()
            //2. Use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)

//            val options = makeSceneTransitionAnimation(
//                context,
//                (tvTitle as View?)!!, "profile"
//            )
            intent.putExtra("MOVIE_EXTRA", movie)

            context.startActivity(intent)

        }
    }


}
