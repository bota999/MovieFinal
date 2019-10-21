package kz.movieapp.moviedb.detail

import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.Videos
import kz.movieapp.moviedb.models.response.MovieResponse

interface DetailView {
    fun showMovieDetails(movies : MovieDetail?)
    fun getVideos(videos: List<Videos>?)
    fun showSimilarMovies(movies: MovieResponse?)
}