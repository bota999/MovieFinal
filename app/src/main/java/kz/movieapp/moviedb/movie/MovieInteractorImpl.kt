package kz.movieapp.moviedb.movie

import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.models.response.GenreList
import rx.Observable

class MovieInteractorImpl(private val movieDbApi: MovieDbApi) : MovieInteractor {

    override fun getSearchedMovie(query: String): Observable<MovieResponse> {
        return movieDbApi.getSearchedMovie(createSearchMovieQueryMap(query))
    }

    override fun getGenreList(): Observable<GenreList> {
        return movieDbApi.getGenreList(createGenreListQueryMap())
    }

    override fun getUpcomingMovies(page: Int): Observable<MovieResponse> {
        return movieDbApi.getUpcomingMovie(createQueryMap(page))
    }

    override fun getPopularMovies(page: Int): Observable<MovieResponse> {
        return movieDbApi.getPopularMovie(createQueryMap(page))
    }

    override fun getNowPlayingMovies(): Observable<MovieResponse> {
        return movieDbApi.getNowPlayingMovie(createQueryMap(1))
    }

    override fun getLatestMovies(): Observable<Movie> {
        return movieDbApi.getLatestMovie(createQueryMap(1))
    }

    override fun getGenreFilter(id: String, page: Int): Observable<MovieResponse> {
        return movieDbApi.getGenreFilter(createGenreFilterQueryMap(id, page))
    }

    override fun getMovieFilter(from: String, to: String, request: String, page: Int): Observable<MovieResponse> {
        return movieDbApi.getGenreFilter(createMovieFilterQueryMap(from,to,request))
    }

    fun createGenreListQueryMap(): Map<String, String> {
        return hashMapOf(
            "language" to "ru"
        )
    }

    fun createGenreFilterQueryMap(id: String, page: Int): Map<String, String> {
        return hashMapOf(
            "page" to page.toString(),
            "language" to "ru",
            "with_genres" to id,
            "sort_by" to "popularity.desc"
        )
    }
    fun createMovieFilterQueryMap(from: String, to: String, request: String): Map<String, String> {
        return hashMapOf(
            "primary_release_date.gte" to from,
            "primary_release_date.lte" to to,
            "language" to "ru",
            "with_genres" to request,
            "sort_by" to "popularity.desc"
        )
    }

    private fun createQueryMap(page: Int): Map<String, String> {
        return hashMapOf(
            "page" to page.toString(),
            "language" to "ru",
            "sort_by" to "popularity.desc"
        )
    }

    private fun createSearchMovieQueryMap(query: String): Map<String, String> {
        return hashMapOf(
            "query" to query,
            "language" to "ru",
            "sort_by" to "popularity.desc"
        )
    }

}

