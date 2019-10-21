package kz.movieapp.moviedb.api

import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.models.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import rx.Observable

interface MovieDbApi {

    @GET("genre/movie/list")
    fun getGenreList(@QueryMap map : Map<String, String>): Observable<GenreList>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("discover/movie")
    fun getGenreFilter(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/latest")
    fun getLatestMovie(@QueryMap map : Map<String, String>): Observable<Movie>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: String, @QueryMap map: Map<String, String>): Observable<MovieDetail>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") id: String): Observable<VideoResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") id: String, @QueryMap map: Map<String, String>): Observable<MovieResponse>

    @GET("search/movie")
    fun getSearchedMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>
}