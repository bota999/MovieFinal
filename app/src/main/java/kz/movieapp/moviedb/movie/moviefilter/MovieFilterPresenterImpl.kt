package kz.movieapp.moviedb.movie.moviefilter

import android.util.Log
import kz.movieapp.moviedb.models.Genre
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.MovieInteractor
import kz.movieapp.moviedb.search_history.SearchHistoryFragment
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MovieFilterPresenterImpl(private val interactor: MovieInteractor, private var view: MovieFilterFragmentView?) :
    MovieFilterPresenter {

    override fun prepareGenreList(view: MovieFilterFragment) {
        this.view = view
        getGenreListFromRequest()
    }

//    override fun filterMovie(from: String, to: String, request: String) {
//        var fr = "$from-01-01"
//        var t = "$to-01-01"
//        interactor.getMovieFilter(fr,t,request)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { response -> onGetMovieFilterListSuccess(response)},
//                { e -> onGetMovieFilterFailure(e) }
//            )
//    }
//
//    private fun onGetMovieFilterFailure(e: Throwable?) {
//
//    }
//
//    private fun onGetMovieFilterListSuccess(response: MovieResponse?) {
//        view?.showResult(response)
//    }

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
        val emp : List<Genre> = listOf()
        val emptyList = GenreList(emp)
        view?.showGenreList(emptyList)
    }

    private fun onGetGenreListSuccess(response: GenreList) {
        view?.showGenreList(response)
    }
}