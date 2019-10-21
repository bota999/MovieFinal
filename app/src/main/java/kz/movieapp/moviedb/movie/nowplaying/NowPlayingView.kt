package kz.movieapp.moviedb.movie.nowplaying


import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.models.response.MovieResponse

interface NowPlayingView {
    fun showFilteredMovies(movieResponse: MovieResponse?)
    fun showLoading()
}