package kz.movieapp.moviedb.movie.latestmovie

import android.util.Log
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LatestMoviePresenterImpl(private val interactor: MovieInteractor, private var view: LatestMovieView?): LatestMoviePresenter {

    override fun setView(LatestMovieView: LatestMovieView) {
        view = LatestMovieView
        getLatestMovieMovies()
    }

    private fun getLatestMovieMovies(){
        interactor.getLatestMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { popularMoviesResponse -> onGetLatestMovieMoviesSuccess(popularMoviesResponse)},
                { e -> onGetLatestMovieMoviesFailure(e) }
            )
    }

    private fun onGetLatestMovieMoviesFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetLatestMovieMoviesSuccess(movieResponse: Movie) {
        var movies : ArrayList<Movie> = ArrayList()
        movies.add(movieResponse)

        view?.showLatestMovie(movies)
    }
}