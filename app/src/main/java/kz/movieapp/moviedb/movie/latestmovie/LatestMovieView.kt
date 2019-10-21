package kz.movieapp.moviedb.movie.latestmovie

import kz.movieapp.moviedb.models.Movie

interface LatestMovieView{
    fun showLatestMovie(movies : ArrayList<Movie>?)
}