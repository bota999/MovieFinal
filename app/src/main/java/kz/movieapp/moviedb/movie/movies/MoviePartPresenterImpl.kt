package kz.movieapp.moviedb.movie.movies

import kz.movieapp.moviedb.movie.MovieInteractor

class MoviePartPresenterImpl(private val interactor: MovieInteractor, private var view: MoviePartView?) :
    MoviePartPresenter {
    override fun setView(moviePartView: MoviePartView) {
        view = moviePartView
        getTabs()
    }

    private fun getTabs() {
        view?.getTabs()
    }
}