package kz.movieapp.moviedb.models.response

import com.google.gson.annotations.SerializedName
import kz.movieapp.moviedb.models.Movie

data class MovieResponse (
    @SerializedName("results")
    var movies:  ArrayList<Movie>
)