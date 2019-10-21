package kz.movieapp.moviedb.models.response

import com.google.gson.annotations.SerializedName
import kz.movieapp.moviedb.models.Genre

data class GenreList (
    @SerializedName("genres") val genres : List<Genre>
)

