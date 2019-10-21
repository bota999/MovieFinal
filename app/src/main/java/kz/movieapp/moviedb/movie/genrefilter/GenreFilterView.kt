package kz.movieapp.moviedb.movie.genrefilter

import kz.movieapp.moviedb.models.Movie

interface GenreFilterView{
    fun showGenreFilterMovie(movies :  ArrayList<Movie>?)
    fun showLoading()
}