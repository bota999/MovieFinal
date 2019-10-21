package kz.movieapp.moviedb.movie.genre

import kz.movieapp.moviedb.models.response.GenreList

interface GenrePartView {
    fun showGenres(genreList: GenreList)
}