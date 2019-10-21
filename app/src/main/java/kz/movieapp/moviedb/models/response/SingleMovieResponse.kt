package kz.movieapp.moviedb.models.response

import com.google.gson.annotations.SerializedName
import kz.movieapp.moviedb.models.Movie

data class SingleMovieResponse(
    var movies: ArrayList<Movie>
)