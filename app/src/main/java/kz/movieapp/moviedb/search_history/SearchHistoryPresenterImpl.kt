package kz.movieapp.moviedb.search_history

import android.util.Log
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import kz.movieapp.moviedb.search_history.SearchHistoryFragment
import kz.movieapp.moviedb.search_history.SearchHistoryFragmentView
import kz.movieapp.moviedb.search_history.SearchHistoryPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SearchHistoryPresenterImpl(private val interactor: MovieInteractor, private var view: SearchHistoryFragmentView?) :
    SearchHistoryPresenter {


    override fun searchMovie(view: SearchHistoryFragment, query: String) {
        this.view = view
        getSearchedMovieFromRequest(query)
    }

    private fun getSearchedMovieFromRequest(query: String) {
        interactor.getSearchedMovie(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> onGetSearchedSuccess(response)},
                { e -> onGetSearchedFailure(e) }
            )
    }

    private fun onGetSearchedFailure(e: Throwable?) {
        Log.e("Error", e.toString())
        val emptyList = MovieResponse(arrayListOf())
        view?.showSearchedResult(emptyList)
    }

    private fun onGetSearchedSuccess(response: MovieResponse?) {
        view?.showSearchedResult(response)
    }

}