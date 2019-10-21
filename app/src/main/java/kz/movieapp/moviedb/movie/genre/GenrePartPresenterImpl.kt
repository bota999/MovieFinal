package kz.movieapp.moviedb.movie.genre

import android.util.Log
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.movie.MovieInteractor
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GenrePartPresenterImpl(private val interactor: MovieInteractor, private var view: GenrePartView?) :
    GenrePartPresenter {
    override fun setView(genrePartView: GenrePartView) {
        view = genrePartView
        getGenreListFromRequest()
    }

    private fun getGenreListFromRequest() {
        interactor.getGenreList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> onGetGenreListSuccess(response)},
                { e -> onGetGenreListFailure(e) }
            )
    }

    private fun onGetGenreListFailure(e: Throwable?) {
        Log.e("Error", e.toString())
    }

    private fun onGetGenreListSuccess(response: GenreList) {
        Log.d("Genre List", "$response")
        view?.showGenres(response)
    }
}