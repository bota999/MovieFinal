package kz.movieapp.moviedb.models

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id") val id : Int,
    @SerializedName("name") var name: String
) {
    val _name: String
        get() = this.name.capitalize()
}

data class GenreShow(
    val ids : String
)