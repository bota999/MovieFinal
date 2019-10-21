package kz.movieapp.moviedb.detail

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kz.movieapp.moviedb.models.response.VideoResponse
import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.response.MovieResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailPresenterImpl(val interactor: DetailInteractor, private var view: DetailView?): DetailPresenter{

    override fun setView(mainView: DetailView, id: String) {
        view = mainView

        GlobalScope.launch(Dispatchers.IO) {
            val w1 = async {
                getMovieDetails(id)
            }
            val w2 = async{
                getMovieVideos(id)
            }
            val w3 = async {
                getSimilarMovies(id)
            }
            w1.await()
            w2.await()
            w3.await()
        }
    }

    private fun getSimilarMovies(id: String) {
        interactor.getSimilarMovies(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> onGetSimilarMoviesSuccess(response)},
                { e -> onGetSimilarMoviesFailure(e) }
            )
    }

    private fun onGetSimilarMoviesFailure(e: Throwable?) {

    }

    private fun onGetSimilarMoviesSuccess(response: MovieResponse?) {
        view?.showSimilarMovies(response)
    }


    //
    private fun getMovieDetails(id: String){
        interactor.getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieDetailResponse -> onGetMovieDetailsSuccess(movieDetailResponse)},
                { e -> onGetMovieDetailsFailure(e) }
            )
    }

    private fun onGetMovieDetailsFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetMovieDetailsSuccess(moviesResponse: MovieDetail?) {
        view?.showMovieDetails(moviesResponse)
    }

    private fun getMovieVideos(id: String){
        interactor.getMovieVideos(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { videos -> onGetVideosSuccess(videos)},
                { e -> onGetVideosFailure(e) }
            )
    }


    private fun onGetVideosFailure(e: Throwable?) {
        Log.e(e?.message, e?.stackTrace.toString())
    }

    private fun onGetVideosSuccess(videoResponse: VideoResponse?) {
        view?.getVideos(videoResponse?.videos)
    }

}