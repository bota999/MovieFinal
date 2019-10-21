package kz.movieapp.moviedb.detail

import kz.movieapp.moviedb.models.response.VideoResponse
import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.response.MovieResponse
import rx.Observable

interface DetailInteractor {
    fun getMovieDetails(id: String): Observable<MovieDetail>
    fun getMovieVideos(id: String): Observable<VideoResponse>
    fun getSimilarMovies(id: String): Observable<MovieResponse>
}