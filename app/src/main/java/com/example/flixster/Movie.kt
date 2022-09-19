package com.example.flixster

import android.content.res.Resources
import org.json.JSONArray

data class Movie(
    val movieId: Int,
    val title: String,
    private val posterPath: String,
    private val backdropPath: String,
    val overview: String,
) {

    val posterImageUrl: String = "https://image.tmdb.org/t/p/w342/$posterPath"
    val backDropImageUrl: String = "https://image.tmdb.org/t/p/w780/$backdropPath"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie>{
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("title"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("backdrop_path"),
                        movieJson.getString("overview"),
                    )
                )
            }
            return movies

        }
    }
}