package kz.movieapp.moviedb.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path") val posterPath: String,
    val id: String,
    val title: String
) {
    constructor() : this("", "", "")
    fun getPosterUrl(): String {
        return "http://image.tmdb.org/t/p/w500$posterPath"
    }
}