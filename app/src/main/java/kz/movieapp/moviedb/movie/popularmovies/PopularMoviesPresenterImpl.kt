package kz.movieapp.moviedb.movie.popularmovies

import android.util.Log
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PopularMoviesPresenterImpl(private val interactor: MovieInteractor, private var view: PopularMoviesView?): PopularMoviesPresenter {
    override fun loadMore(page: Int) {
        getPopularMovies(page)
    }

    override fun setView(popularMoviesView: PopularMoviesView) {
        view = popularMoviesView
        getPopularMovies(1)
    }

    private fun getPopularMovies(page: Int){
        interactor.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { popularMoviesResponse -> onGetPopularMoviesMoviesSuccess(popularMoviesResponse)},
                { e -> onGetPopularMoviesMoviesFailure(e) }
            )
    }

    private fun onGetPopularMoviesMoviesFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetPopularMoviesMoviesSuccess(moviesResponse: MovieResponse?) {
        view?.showPopularMovies(moviesResponse?.movies)
    }
}